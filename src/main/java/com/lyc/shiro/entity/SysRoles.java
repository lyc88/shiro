package com.lyc.shiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
* @date 2019-12-28 11:25:16
*/
@Data
@TableName("sys_roles")
public class SysRoles{
    /**
    * 角色编号
    */
    @ApiModelProperty(value = "角色编号" ,required = true)
    @NotNull(message = "角色编号不能为空")
    @TableId
    private Long id;

    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称",required = true)
    @NotNull(message = "角色名称不能为空")
    @TableField("role")
    private String role;

    /**
    * 角色描述
    */
    @ApiModelProperty(value = "角色描述",required = true)
    @NotNull(message = "角色描述不能为空")
    @TableField("description")
    private String description;

    /**
    * 父节点
    */
    @ApiModelProperty(value = "父节点",required = true)
    @NotNull(message = "父节点不能为空")
    @TableField("pid")
    private Long pid;

    /**
    * 是否锁定
    */
    @ApiModelProperty(value = "是否锁定",required = true)
    @NotNull(message = "是否锁定不能为空")
    @TableField("available")
    private Integer available;

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