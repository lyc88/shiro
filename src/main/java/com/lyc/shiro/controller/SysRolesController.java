package com.lyc.shiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lyc.shiro.common.CommonResult;
import com.lyc.shiro.common.CommonResultResponse;
import com.lyc.shiro.entity.SysRoles;
import com.lyc.shiro.service.SysRolesService;
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
* @date 2019-12-28 11:25:16
*/
@Api(value = "角色操作",tags={"角色操作接口"})
@RestController
public class SysRolesController{

    @Autowired
    private SysRolesService sysRolesService;

    /**
    * 分页查询 size 页大小 current 当前页
    */
    @ApiOperation(value = "分页",notes = "通用结果返回对象")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "size", value = "页大小 默认10", defaultValue = "10"),
    @ApiImplicitParam(name = "current", value = "当前页 默认1", defaultValue = "1")
    })
    @GetMapping("sysRoles/page")
    public CommonResult<SysRoles> page(@RequestParam(name = "size",defaultValue = "10") Integer size,
                                       @RequestParam(name = "current",defaultValue = "1") Integer current){
        Page page = new Page(current,size);
        IPage result = sysRolesService.page(page);
        List<SysRoles>  sysRolesList = result.getRecords();
        return  CommonResultResponse.ok(sysRolesList);
    }

    /**
    * 列表
    */
    @ApiOperation(value = "列表",notes = "通用结果返回对象")
    @GetMapping("sysRoles/list")
    public CommonResult<SysRoles> list(){
        List<SysRoles> sysRolesList = sysRolesService.list(new QueryWrapper<SysRoles>());
        return  CommonResultResponse.ok(sysRolesList);
    }

    /**
    *  id删除
    */
    @ApiOperation(value = "id删除",notes = "通用结果返回对象")
    @PostMapping("sysRoles/delete")
    public CommonResult<Boolean> delete(Integer id){
        Boolean success = sysRolesService.removeById(id);
        return CommonResultResponse.ok(success);
    }

    /**
    *  id查询
    */
    @ApiOperation(value = "id查询",notes = "通用结果返回对象")
    @GetMapping("sysRoles/findById")
    public CommonResult<SysRoles> findById(Integer id){
        SysRoles sysRoles = sysRolesService.getById(id);
        return CommonResultResponse.ok(sysRoles);
    }

     /**
      *  新增
      */
     @ApiOperation(value = "新增",notes = "通用结果返回对象")
     @PostMapping("sysRoles/add")
     public CommonResult<Boolean> add(SysRoles sysRoles){
        Boolean success = sysRolesService.save(sysRoles);
        return CommonResultResponse.ok(success);
     }
}