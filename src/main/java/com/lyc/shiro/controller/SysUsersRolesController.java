package com.lyc.shiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lyc.shiro.common.CommonResult;
import com.lyc.shiro.common.CommonResultResponse;
import com.lyc.shiro.entity.SysUsersRoles;
import com.lyc.shiro.service.SysUsersRolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* 
* @author lyc
* @date 2019-12-28 11:25:53
*/
@Api(value = "用户与角色操作",tags={"用户与角色接口"})
@RestController
public class SysUsersRolesController{

    @Autowired
    private SysUsersRolesService sysUsersRolesService;

    /**
    * 分页查询 size 页大小 current 当前页
    */
    @ApiOperation(value = "分页",notes = "通用结果返回对象")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "size", value = "页大小 默认10", defaultValue = "10"),
    @ApiImplicitParam(name = "current", value = "当前页 默认1", defaultValue = "1")
    })
    @GetMapping("sysUsersRoles/page")
    public CommonResult<SysUsersRoles> page(@RequestParam(name = "size",defaultValue = "10") Integer size,
                                            @RequestParam(name = "current",defaultValue = "1") Integer current){
        Page page = new Page(current,size);
        IPage result = sysUsersRolesService.page(page);
        List<SysUsersRoles>  sysUsersRolesList = result.getRecords();
        return  CommonResultResponse.ok(sysUsersRolesList);
    }

    /**
    * 列表
    */
    @ApiOperation(value = "列表",notes = "通用结果返回对象")
    @GetMapping("sysUsersRoles/list")
    public CommonResult<SysUsersRoles> list(){
        List<SysUsersRoles> sysUsersRolesList = sysUsersRolesService.list(new QueryWrapper<SysUsersRoles>());
        return  CommonResultResponse.ok(sysUsersRolesList);
    }

    /**
    *  id删除
    */
    @ApiOperation(value = "id删除",notes = "通用结果返回对象")
    @PostMapping("sysUsersRoles/delete")
    public CommonResult<Boolean> delete(Integer id){
        Boolean success = sysUsersRolesService.removeById(id);
        return CommonResultResponse.ok(success);
    }

    /**
    *  id查询
    */
    @ApiOperation(value = "id查询",notes = "通用结果返回对象")
    @GetMapping("sysUsersRoles/findById")
    public CommonResult<SysUsersRoles> findById(Integer id){
        SysUsersRoles sysUsersRoles = sysUsersRolesService.getById(id);
        return CommonResultResponse.ok(sysUsersRoles);
    }

     /**
      *  新增
      */
     @ApiOperation(value = "新增",notes = "通用结果返回对象")
     @PostMapping("sysUsersRoles/add")
     public CommonResult<Boolean> add(SysUsersRoles sysUsersRoles){
        Boolean success = sysUsersRolesService.save(sysUsersRoles);
        return CommonResultResponse.ok(success);
     }
}