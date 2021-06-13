package com.yang.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.shiro.dao.PermMapper;
import com.yang.shiro.entity.Perm;
import com.yang.shiro.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PERM (Perm)表服务实现类
 *
 * @author makejava
 * @since 2021-06-13 09:59:21
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper,Perm> implements PermService {
    @Autowired
    private PermMapper permMapper;
}