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
* @date 2019-12-28 11:24:52
*/
@Data
@TableName("sys_permissions")
public class SysPermissions{
    /**
    * 编号
    */
    @ApiModelProperty(value = "编号" ,required = true)
    @NotNull(message = "编号不能为空")
    @TableId
    private Long id;

    /**
    * 权限编号
    */
    @ApiModelProperty(value = "权限编号",required = true)
    @NotNull(message = "权限编号不能为空")
    @TableField("permission")
    private String permission;

    /**
    * 权限描述
    */
    @ApiModelProperty(value = "权限描述",required = true)
    @NotNull(message = "权限描述不能为空")
    @TableField("description")
    private String description;

    /**
    * 此权限关联角色的id
    */
    @ApiModelProperty(value = "此权限关联角色的id",required = true)
    @NotNull(message = "此权限关联角色的id不能为空")
    @TableField("rid")
    private Long rid;

    /**
    * 类型 1 菜单 2 按钮
    */
    @ApiModelProperty(value = "类型 1 菜单 2 按钮",required = true)
    @NotNull(message = "类型 1 菜单 2 按钮不能为空")
    @TableField("type")
    private String type;

    /**
    * url
    */
    @ApiModelProperty(value = "url",required = true)
    @NotNull(message = "url不能为空")
    @TableField("url")
    private String url;

    /**
    * 排序字段
    */
    @ApiModelProperty(value = "排序字段",required = true)
    @NotNull(message = "排序字段不能为空")
    @TableField("sort")
    private Integer sort;

    /**
    * 是否外部链接 1 是 0不是
    */
    @ApiModelProperty(value = "是否外部链接 1 是 0不是",required = true)
    @NotNull(message = "是否外部链接 1 是 0不是不能为空")
    @TableField("external")
    private Integer external;

    /**
    * 父级id
    */
    @ApiModelProperty(value = "父级id",required = true)
    @NotNull(message = "父级id不能为空")
    @TableField("parent_id")
    private Long parentId;

    /**
    * 是否锁定
    */
    @ApiModelProperty(value = "是否锁定",required = true)
    @NotNull(message = "是否锁定不能为空")
    @TableField("available")
    private Integer available;

    /**
    * 菜单图标
    */
    @ApiModelProperty(value = "菜单图标",required = true)
    @NotNull(message = "菜单图标不能为空")
    @TableField("icon")
    private String icon;

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