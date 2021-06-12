package com.yang.shiro.controller;

import com.yang.shiro.entity.User;
import com.yang.shiro.service.UserService;
import com.yang.shiro.config.shiro.jwt.JwtUtils;
import com.yang.shiro.util.result.Result;
import com.yang.shiro.util.result.ResultCode;
import com.yang.shiro.vo.login.LoginRegisterVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
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

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if (StringUtils.isEmpty(user.getPhone())||StringUtils.isEmpty(user.getPassword())){
            return Result.error(ResultCode.PARAM_IS_BLANK);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(),user.getPassword());
        try {
            subject.login(token);
            String sign = JwtUtils.sign(user.getPhone());
            return Result.success(sign);
        } catch (UnknownAccountException uae) { // 账号不存在
            return Result.error(ResultCode.USER_LOGIN_ERROR);
        } catch (IncorrectCredentialsException ice) { // 账号与密码不匹配
            return Result.error(ResultCode.USER_LOGIN_ERROR);
        }
    }

    @PostMapping("/test")
    public Result test(){
        return Result.success("test");
    }

}