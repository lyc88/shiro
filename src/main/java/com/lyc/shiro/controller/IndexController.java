package com.lyc.shiro.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyc.shiro.common.CommonResult;
import com.lyc.shiro.common.CommonResultResponse;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Controller
@Validated
public class IndexController {

    /**
     * 未认证 处理
     * @return
     */
   /* @ResponseBody
    @GetMapping("user/unauth")
    public CommonResult unauth(){
        throw new UnauthenticatedException();
    }*/


    @GetMapping("user/unauth")
    public String unauth(){
        return "login";
    }

    /**
     * 未授权处理
     * @return
     */
    @ResponseBody
    @GetMapping("user/unperms")
    public CommonResult unperms(){
        throw new UnauthorizedException();
    }

    /**
     * 登入页面
     * @return
     */
    @GetMapping("user/index")
    public String login(){
       return "login";
    }

    /**
     * 登入成功
     * @return
     */
    @GetMapping("index")
    public String index(){
        return "index";
    }

    @RequiresPermissions(value = "menus:list")
    @GetMapping("menus/list")
    @ResponseBody
    public CommonResult getMenus(){
        return CommonResultResponse.ok("menus:list","获取成功");
    }

    @RequiresPermissions(value = "user:add")
    @ResponseBody
    @GetMapping("user/add")
    public CommonResult userAdd(){
        return CommonResultResponse.ok("user:add","获取成功");
    }

    @RequiresPermissions(value = "permissions:list")
    @ResponseBody
    @GetMapping("permissions/list")
    public CommonResult permissionsList(){
        return CommonResultResponse.ok("user:add","获取成功");
    }

    /**
     * 登录接口
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/user/login")
    public CommonResult login(@NotBlank(message = "用户名不能为空") String username, @NotBlank(message = "密码不能为空")String password){

        Subject subject = SecurityUtils.getSubject();
        try {
            // 1. 前后分离思想 登录 成功 可以返回token ，前端根据token 获取菜单 信息，跳转页面
            // 2. 后端 直接跳转
            // 3. 我这里 返回成功 前端直接 跳转 根据cookies
            subject.login( new UsernamePasswordToken(username, password) );
            return CommonResultResponse.ok("登入成功");
        } catch ( UnknownAccountException uae ) {
            return CommonResultResponse.fail("登入成功");

        } catch ( IncorrectCredentialsException ice ) {
            return CommonResultResponse.fail("用户帐号或密码不正确");

        } catch ( LockedAccountException lae ) {
            return CommonResultResponse.fail("用户帐号被锁定不可用");

        } catch ( AuthenticationException ae ) {
            return CommonResultResponse.fail("登录失败："+ae.getMessage());
        }

    }
}
