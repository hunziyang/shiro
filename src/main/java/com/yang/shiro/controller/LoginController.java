package com.yang.shiro.controller;

import com.yang.shiro.service.UserService;
import com.yang.shiro.util.result.Result;
import com.yang.shiro.util.result.ResultCode;
import com.yang.shiro.vo.login.LoginRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-06-12 16:34:26
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result loginRegister(@RequestBody LoginRegisterVo loginRegisterVo) {
        boolean flag = userService.loginRegister(loginRegisterVo);
        if (!flag) {
            return Result.error(ResultCode.FAILED, flag);
        }
        return Result.success(flag);
    }

}