package com.yang.shiro.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * ROLE (Role)实体类
 *
 * @author makejava
 * @since 2021-06-13 09:59:22
 */
@Data
@TableName("role")
public class Role {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * ROLE_NAME
     */
    private String roleName;
    /**
     * ROLE_KEY
     */
    private String roleKey;
    /**
     * DESCRIPTION
     */
    private String description;
    /**
     * 乐观锁
     */
    private Integer revision;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(value = "CREATED_TIME", fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    @TableField(value = "UPDATED_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}