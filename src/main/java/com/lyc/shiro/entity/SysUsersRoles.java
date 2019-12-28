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
* @date 2019-12-28 11:25:53
*/
@Data
@TableName("sys_users_roles")
public class SysUsersRoles{
    /**
    * 编号
    */
    @ApiModelProperty(value = "编号" ,required = true)
    @NotNull(message = "编号不能为空")
    @TableId
    private Long id;

    /**
    * 用户编号
    */
    @ApiModelProperty(value = "用户编号",required = true)
    @NotNull(message = "用户编号不能为空")
    @TableField("user_id")
    private Long userId;

    /**
    * 角色编号
    */
    @ApiModelProperty(value = "角色编号",required = true)
    @NotNull(message = "角色编号不能为空")
    @TableField("role_id")
    private Long roleId;

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