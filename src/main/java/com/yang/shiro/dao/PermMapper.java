package com.yang.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.shiro.entity.Perm;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * PERM (Perm)表数据库访问层
 *
 * @author makejava
 * @since 2021-06-13 09:59:21
 */
public interface PermMapper extends BaseMapper<Perm> {

    Set<Perm> selectPremByPhone(@Param("phone") String phone);
}