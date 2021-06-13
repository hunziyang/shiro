package com.yang.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.shiro.dao.RoleMapper;
import com.yang.shiro.entity.Role;
import com.yang.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ROLE (Role)表服务实现类
 *
 * @author makejava
 * @since 2021-06-13 09:59:22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
}