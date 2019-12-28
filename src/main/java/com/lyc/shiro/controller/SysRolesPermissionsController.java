package com.lyc.shiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lyc.shiro.common.CommonResult;
import com.lyc.shiro.common.CommonResultResponse;
import com.lyc.shiro.entity.SysRolesPermissions;
import com.lyc.shiro.service.SysRolesPermissionsService;
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
* @date 2019-12-28 11:25:32
*/
@Api(value = "角色与权限操作",tags={"角色与权限操作接口"})
@RestController
public class SysRolesPermissionsController{

    @Autowired
    private SysRolesPermissionsService sysRolesPermissionsService;

    /**
    * 分页查询 size 页大小 current 当前页
    */
    @ApiOperation(value = "分页",notes = "通用结果返回对象")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "size", value = "页大小 默认10", defaultValue = "10"),
    @ApiImplicitParam(name = "current", value = "当前页 默认1", defaultValue = "1")
    })
    @GetMapping("sysRolesPermissions/page")
    public CommonResult<SysRolesPermissions> page(@RequestParam(name = "size",defaultValue = "10") Integer size,
                                                  @RequestParam(name = "current",defaultValue = "1") Integer current){
        Page page = new Page(current,size);
        IPage result = sysRolesPermissionsService.page(page);
        List<SysRolesPermissions>  sysRolesPermissionsList = result.getRecords();
        return  CommonResultResponse.ok(sysRolesPermissionsList);
    }

    /**
    * 列表
    */
    @ApiOperation(value = "列表",notes = "通用结果返回对象")
    @GetMapping("sysRolesPermissions/list")
    public CommonResult<SysRolesPermissions> list(){
        List<SysRolesPermissions> sysRolesPermissionsList = sysRolesPermissionsService.list(new QueryWrapper<SysRolesPermissions>());
        return  CommonResultResponse.ok(sysRolesPermissionsList);
    }

    /**
    *  id删除
    */
    @ApiOperation(value = "id删除",notes = "通用结果返回对象")
    @PostMapping("sysRolesPermissions/delete")
    public CommonResult<Boolean> delete(Integer id){
        Boolean success = sysRolesPermissionsService.removeById(id);
        return CommonResultResponse.ok(success);
    }

    /**
    *  id查询
    */
    @ApiOperation(value = "id查询",notes = "通用结果返回对象")
    @GetMapping("sysRolesPermissions/findById")
    public CommonResult<SysRolesPermissions> findById(Integer id){
        SysRolesPermissions sysRolesPermissions = sysRolesPermissionsService.getById(id);
        return CommonResultResponse.ok(sysRolesPermissions);
    }

     /**
      *  新增
      */
     @ApiOperation(value = "新增",notes = "通用结果返回对象")
     @PostMapping("sysRolesPermissions/add")
     public CommonResult<Boolean> add(SysRolesPermissions sysRolesPermissions){
        Boolean success = sysRolesPermissionsService.save(sysRolesPermissions);
        return CommonResultResponse.ok(success);
     }
}