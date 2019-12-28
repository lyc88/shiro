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
* @date 2019-12-28 11:25:43
*/
@Data
@TableName("sys_users")
public class SysUsers{
    /**
    * 编号
    */
    @ApiModelProperty(value = "编号" ,required = true)
    @NotNull(message = "编号不能为空")
    @TableId
    private Long id;

    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名",required = true)
    @NotNull(message = "用户名不能为空")
    @TableField("username")
    private String username;

    /**
    * 密码
    */
    @ApiModelProperty(value = "密码",required = true)
    @NotNull(message = "密码不能为空")
    @TableField("password")
    private String password;

    /**
    * 盐值
    */
    @ApiModelProperty(value = "盐值",required = true)
    @NotNull(message = "盐值不能为空")
    @TableField("salt")
    private String salt;

    /**
    * 角色列表
    */
    @ApiModelProperty(value = "角色列表",required = true)
    @NotNull(message = "角色列表不能为空")
    @TableField("role_id")
    private String roleId;

    /**
    * 是否锁定
    */
    @ApiModelProperty(value = "是否锁定",required = true)
    @NotNull(message = "是否锁定不能为空")
    @TableField("locked")
    private Integer locked;

    /**
    * 注册IP
    */
    @ApiModelProperty(value = "注册IP",required = true)
    @NotNull(message = "注册IP不能为空")
    @TableField("reg_ip")
    private String regIp;

    /**
    * 最近登录IP
    */
    @ApiModelProperty(value = "最近登录IP",required = true)
    @NotNull(message = "最近登录IP不能为空")
    @TableField("last_login_ip")
    private String lastLoginIp;

    /**
    * 最近登录时间
    */
    @ApiModelProperty(value = "最近登录时间",required = true)
    @NotNull(message = "最近登录时间不能为空")
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
    * 登录次数
    */
    @ApiModelProperty(value = "登录次数",required = true)
    @NotNull(message = "登录次数不能为空")
    @TableField("login_count")
    private Integer loginCount;

    /**
    * 注册时间
    */
    @ApiModelProperty(value = "注册时间",required = true)
    @NotNull(message = "注册时间不能为空")
    @TableField("create_time")
    private Date createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间",required = true)
    @NotNull(message = "更新时间不能为空")
    @TableField("update_time")
    private Date updateTime;

    /**
    * 版本号
    */
    @ApiModelProperty(value = "版本号",required = true)
    @NotNull(message = "版本号不能为空")
    @TableField("version")
    private Integer version;

}