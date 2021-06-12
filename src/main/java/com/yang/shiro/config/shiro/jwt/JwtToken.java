package com.yang.shiro.config.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
    // 加密后的 JWT token串
    private String token;

    private String phone;
    public JwtToken(String token) {
        this.token = token;
        this.phone = JwtUtils.getClaimFiled(token, "phone");
    }
    @Override
    public Object getPrincipal() {
        return this.phone;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
