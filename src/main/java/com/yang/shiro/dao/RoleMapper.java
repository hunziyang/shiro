package com.yang.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.shiro.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * ROLE (Role)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-13 09:59:22
 */
public interface RoleMapper extends BaseMapper<Role> {

    Set<Role> selectRolesByPhone(@Param("phone") String phone);
}