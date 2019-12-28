package com.lyc.shiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.shiro.common.CommonResult;
import com.lyc.shiro.common.CommonResultResponse;
import com.lyc.shiro.entity.SysPermissions;
import com.lyc.shiro.service.SysPermissionsService;
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
* @date 2019-12-28 11:24:52
*/
@Api(value = "角色操作",tags={"角色操作接口"})
@RestController
public class SysPermissionsController{

    @Autowired
    private SysPermissionsService sysPermissionsService;

    /**
    * 分页查询 size 页大小 current 当前页
    */
    @ApiOperation(value = "分页",notes = "通用结果返回对象")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "size", value = "页大小 默认10", defaultValue = "10"),
    @ApiImplicitParam(name = "current", value = "当前页 默认1", defaultValue = "1")
    })
    @GetMapping("sysPermissions/page")
    public CommonResult<SysPermissions> page(@RequestParam(name = "size",defaultValue = "10") Integer size,
                                                @RequestParam(name = "current",defaultValue = "1") Integer current){
        Page page = new Page(current,size);
        IPage result = sysPermissionsService.page(page);
        List<SysPermissions>  sysPermissionsList = result.getRecords();
        return  CommonResultResponse.ok(sysPermissionsList);
    }

    /**
    * 列表
    */
    @ApiOperation(value = "列表",notes = "通用结果返回对象")
    @GetMapping("sysPermissions/list")
    public CommonResult<SysPermissions> list(){
        List<SysPermissions> sysPermissionsList = sysPermissionsService.list(new QueryWrapper<SysPermissions>());
        return  CommonResultResponse.ok(sysPermissionsList);
    }

    /**
    *  id删除
    */
    @ApiOperation(value = "id删除",notes = "通用结果返回对象")
    @PostMapping("sysPermissions/delete")
    public CommonResult<Boolean> delete(Integer id){
        Boolean success = sysPermissionsService.removeById(id);
        return CommonResultResponse.ok(success);
    }

    /**
    *  id查询
    */
    @ApiOperation(value = "id查询",notes = "通用结果返回对象")
    @GetMapping("sysPermissions/findById")
    public CommonResult<SysPermissions> findById(Integer id){
        SysPermissions sysPermissions = sysPermissionsService.getById(id);
        return CommonResultResponse.ok(sysPermissions);
    }

     /**
      *  新增
      */
     @ApiOperation(value = "新增",notes = "通用结果返回对象")
     @PostMapping("sysPermissions/add")
     public CommonResult<Boolean> add(SysPermissions sysPermissions){
        Boolean success = sysPermissionsService.save(sysPermissions);
        return CommonResultResponse.ok(success);
     }
}