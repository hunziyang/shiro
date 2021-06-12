package com.yang.shiro.vo.login;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class LoginRegisterVo {
    private String phone;
    private String password;
    private String username;
    private String mail;
    private Boolean gender;

    public static void main(String[] args) {
        LoginRegisterVo vo = new LoginRegisterVo();
        vo.setGender(true);
        vo.setMail("814230847@qq.com");
        vo.setPassword("123456");
        vo.setPhone("17806171138");
        vo.setUsername("wdy");
        System.out.println(JSON.toJSONString(vo));
    }
}
