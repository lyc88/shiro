package com.lyc.shiro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lyc.shiro.common.CommonResult;
import com.lyc.shiro.common.CommonResultResponse;
import com.lyc.shiro.entity.SysUsers;
import com.lyc.shiro.service.SysUsersService;
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
* @date 2019-12-28 11:25:43
*/
@Api(value = "用户操作",tags={"用户操作接口"})
@RestController
public class SysUsersController{

    @Autowired
    private SysUsersService sysUsersService;

    /**
    * 分页查询 size 页大小 current 当前页
    */
    @ApiOperation(value = "分页",notes = "通用结果返回对象")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "size", value = "页大小 默认10", defaultValue = "10"),
    @ApiImplicitParam(name = "current", value = "当前页 默认1", defaultValue = "1")
    })
    @GetMapping("sysUsers/page")
    public CommonResult<SysUsers> page(@RequestParam(name = "size",defaultValue = "10") Integer size,
                                       @RequestParam(name = "current",defaultValue = "1") Integer current){
        Page page = new Page(current,size);
        IPage result = sysUsersService.page(page);
        List<SysUsers>  sysUsersList = result.getRecords();
        return  CommonResultResponse.ok(sysUsersList);
    }

    /**
    * 列表
    */
    @ApiOperation(value = "列表",notes = "通用结果返回对象")
    @GetMapping("sysUsers/list")
    public CommonResult<SysUsers> list(){
        List<SysUsers> sysUsersList = sysUsersService.list(new QueryWrapper<SysUsers>());
        return  CommonResultResponse.ok(sysUsersList);
    }

    /**
    *  id删除
    */
    @ApiOperation(value = "id删除",notes = "通用结果返回对象")
    @PostMapping("sysUsers/delete")
    public CommonResult<Boolean> delete(Integer id){
        Boolean success = sysUsersService.removeById(id);
        return CommonResultResponse.ok(success);
    }

    /**
    *  id查询
    */
    @ApiOperation(value = "id查询",notes = "通用结果返回对象")
    @GetMapping("sysUsers/findById")
    public CommonResult<SysUsers> findById(Integer id){
        SysUsers sysUsers = sysUsersService.getById(id);
        return CommonResultResponse.ok(sysUsers);
    }

     /**
      *  新增
      */
     @ApiOperation(value = "新增",notes = "通用结果返回对象")
     @PostMapping("sysUsers/add")
     public CommonResult<Boolean> add(SysUsers sysUsers){
        Boolean success = sysUsersService.save(sysUsers);
        return CommonResultResponse.ok(success);
     }
}