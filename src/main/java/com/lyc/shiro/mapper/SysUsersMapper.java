package com.lyc.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lyc.shiro.entity.SysPermissions;
import com.lyc.shiro.entity.SysUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* 
*
* @author lyc
* @date 2019-12-28 11:25:43
*/
@Mapper
public interface SysUsersMapper extends BaseMapper<SysUsers>{

    @Select("select p.* \n" +
            "from sys_users u , sys_users_roles ur,sys_roles_permissions rp,sys_permissions p\n" +
            "where u.id = ur.user_id\n" +
            "and ur.role_id = rp.role_id\n" +
            "and rp.permission_id = p.id\n"+
            "and u.id=#{userId}")
   List<SysPermissions> getPermissions(@Param(value = "userId") Long userId);
}