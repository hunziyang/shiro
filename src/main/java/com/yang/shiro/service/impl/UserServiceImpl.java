package com.yang.shiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.shiro.dao.UserMapper;
import com.yang.shiro.entity.User;
import com.yang.shiro.service.UserService;
import com.yang.shiro.util.salt.SaltUtils;
import com.yang.shiro.vo.login.LoginRegisterVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-06-12 16:34:26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Value("${md5.saltNum}")
    private int salt;
    @Value("${md5.iterations}")
    private int iterations;

    @Override
    public boolean loginRegister(LoginRegisterVo loginRegisterVo) {
        User user = new User();
        BeanUtils.copyProperties(loginRegisterVo,user);
        user.setSalt(SaltUtils.getSalt(salt));
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),user.getSalt(),iterations);
        user.setPassword(md5Hash.toHex());
        try {
            int num = userMapper.insert(user);
            if (num > 0) return true;
        } catch (Exception e) {
            logger.error("registerERR:" + e.getMessage());
        }
        return false;
    }
}