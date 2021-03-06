package com.yang.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.shiro.entity.User;
import org.apache.ibatis.annotations.Param;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-12 16:34:24
 */
public interface UserMapper extends BaseMapper<User> {
    User selectUserByPhone(@Param("phone") String phone);
}