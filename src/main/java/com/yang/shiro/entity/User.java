package com.yang.shiro.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-06-12 16:33:52
 */
@Data
@TableName("user")
public class User {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * PHONE
     */
    private String phone;
    /**
     * PASSWORD
     */
    private String password;
    /**
     * SALT
     */
    private String salt;
    /**
     * USERNAME
     */
    private String username;
    /**
     * MAIL
     */
    private String mail;
    /**
     * GENDER
     */
    private Boolean gender;
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