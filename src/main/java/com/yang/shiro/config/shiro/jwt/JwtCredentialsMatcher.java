package com.yang.shiro.config.shiro.jwt;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置自定义的比对器
 */
public class JwtCredentialsMatcher implements CredentialsMatcher {
    private static final Logger logger = LoggerFactory.getLogger(JwtCredentialsMatcher.class);

    /**
     * 只需要认证该token格式正确且未过期
     * @param authenticationToken
     * @param authenticationInfo
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = authenticationToken.getCredentials().toString();
        String phone = authenticationToken.getPrincipal().toString();
        return JwtUtils.verify(token, phone) && !JwtUtils.isTokenExpired(token);
    }
}
