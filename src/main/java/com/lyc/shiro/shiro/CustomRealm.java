package com.lyc.shiro.shiro;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyc.shiro.entity.SysPermissions;
import com.lyc.shiro.entity.SysUsers;
import com.lyc.shiro.entity.SysUsersRoles;
import com.lyc.shiro.mapper.SysUsersMapper;
import com.lyc.shiro.service.SysUsersRolesService;
import com.lyc.shiro.service.SysUsersService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SysUsersService sysUsersService;

    @Autowired
    private SysUsersMapper usersMapper;


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("请重新登入");
        }
        // principals 就是 认证 传的 第一个 参数 传什么 强转什么
        SysUsers user = (SysUsers) getAvailablePrincipal(principals);

        List<SysPermissions> list = usersMapper.getPermissions(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissions = list.stream().map(e->e.getPermission()).collect(Collectors.toSet());
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken fromToken = (UsernamePasswordToken) token;
        String username = fromToken.getUsername();

        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper();
        List<SysUsers> usersList = sysUsersService.list(queryWrapper);
        if(CollectionUtils.isEmpty(usersList)){
            throw new UnknownAccountException("用户不存在");
        }
        SysUsers user = usersList.get(0);
        // 一般重次 登入 3次 密码错误 就锁定 需要重下 shiro 登入失败 逻辑
        if(user.getLocked()>0){
            throw new LockedAccountException("账户被锁定，请联系管理员");
        }
        //从数据 查询 根据用户username 用户 然后 交给 shiro 去匹配 密码 用户名 相等
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
       /* if (user.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        }*/

        return info;

    }

}
