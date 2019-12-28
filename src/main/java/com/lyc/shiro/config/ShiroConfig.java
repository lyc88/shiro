package com.lyc.shiro.config;

import com.lyc.shiro.filter.MyFormAuthenticationFilter;
import com.lyc.shiro.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    /**
     *  注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     */

    @Bean
    public Realm realm() {
        return new CustomRealm();
    }


    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> chainDefinition = new LinkedHashMap<>();
        chainDefinition.put("/user/index", "anon");
        chainDefinition.put("/user/login", "anon");
        chainDefinition.put("/static/**", "anon");
        chainDefinition.put("/css/**", "anon");
        chainDefinition.put("/js/**", "anon");
        chainDefinition.put("/swagger-ui.html", "anon");
        chainDefinition.put("/doc.html", "anon");
        chainDefinition.put("/swagger-resources", "anon");
        chainDefinition.put("/swagger-resources/configuration/security", "anon");
        chainDefinition.put("/swagger-resources/configuration/ui", "anon");
        chainDefinition.put("/v2/api-docs", "anon");
        chainDefinition.put("/webjars/springfox-swagger-ui/**", "anon");



        // 1、创建过滤器Map，用来装自定义过滤器
        LinkedHashMap<String, Filter> map = new LinkedHashMap<>();
        // 2、将自定义过滤器放入map中，如果实现了自定义授权过滤器，那就必须在这里注册，否则Shiro不会使用自定义的授权过滤器
        map.put("authc", new MyFormAuthenticationFilter());
        // 3、将过滤器Ma绑定到shiroFilterFactoryBean上
        factoryBean.setFilters(map);

        //设置 未认证跳转 当然重写 过滤器 也可以
        factoryBean.setLoginUrl("/user/unauth");
        //设置 未授权跳转
        factoryBean.setUnauthorizedUrl("/user/unperms");

        //除了以上的请求外，其它请求都需要登录
        chainDefinition.put("/**", "authc");
       // chainDefinition.put("/**", "anon");
        factoryBean.setFilterChainDefinitionMap(chainDefinition);
        return factoryBean;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setProxyTargetClass(true);
        creator.setUsePrefix(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //securityManager.setRealms(Arrays.asList(realm()));
        securityManager.setRealm(realm());
        // securityManager.setCacheManager(cacheManager());
        // 设置realm. 可以设置 多ream 和 策略
        //securityManager.setAuthenticator(authenticator());
        return securityManager;
    }

     /*  //添加realm
    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
        authenticator.setRealms(Arrays.asList(jwtRealm()));
        //设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }*/
    /**
     * 使用默认session
     *
     * @return
     */
    @Bean(name="sessionManager")
    public ServletContainerSessionManager servletContainerSessionManager() {
        ServletContainerSessionManager sessionManager = new ServletContainerSessionManager();
        return sessionManager;
    }

  /*  *//**
     *  凭证匹配器
     * @return
     *//*
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher =  new HashedCredentialsMatcher("SHA-256");
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }*/

}
