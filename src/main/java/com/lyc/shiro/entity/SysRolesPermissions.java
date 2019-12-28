package com.lyc.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
* 
*
* @author lyc
* @date 2019-12-28 11:25:32
*/
@Data
@TableName("sys_roles_permissions")
public class SysRolesPermissions{
    /**
    * 编号
    */
    @ApiModelProperty(value = "编号" ,required = true)
    @NotNull(message = "编号不能为空")
    @TableId
    private Long id;

    /**
    * 角色编号
    */
    @ApiModelProperty(value = "角色编号",required = true)
    @NotNull(message = "角色编号不能为空")
    @TableField("role_id")
    private Long roleId;

    /**
    * 权限编号
    */
    @ApiModelProperty(value = "权限编号",required = true)
    @NotNull(message = "权限编号不能为空")
    @TableField("permission_id")
    private Long permissionId;

    /**
    * 添加时间
    */
    @ApiModelProperty(value = "添加时间",required = true)
    @NotNull(message = "添加时间不能为空")
    @TableField("create_time")
    private Date createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间",required = true)
    @NotNull(message = "更新时间不能为空")
    @TableField("update_time")
    private Date updateTime;

}