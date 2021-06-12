package com.yang.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.shiro.entity.User;
import com.yang.shiro.vo.login.LoginRegisterVo;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-06-12 16:34:25
 */
public interface UserService extends IService<User> {

    /**
     * 注册用户
     * @param loginRegisterVo
     * @return
     */
    boolean loginRegister(LoginRegisterVo loginRegisterVo);
}