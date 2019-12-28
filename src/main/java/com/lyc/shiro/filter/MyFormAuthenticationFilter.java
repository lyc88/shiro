package com.lyc.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * https://blog.csdn.net/qq_28082757/article/details/100776165
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
  /*  @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json;charset=utf-8");

        PrintWriter out = httpServletResponse.getWriter();
        JSONObject json = new JSONObject();
        json.put("code","401");
        json.put("msg","登录已失效，请重新登录！");
        out.println(json);
        out.flush();
        out.close();
       //throw new UnauthenticatedException ();
        return false;
    }*/
}
