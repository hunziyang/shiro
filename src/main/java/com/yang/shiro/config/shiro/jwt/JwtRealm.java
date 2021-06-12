package com.yang.shiro.config.shiro.jwt;

import com.yang.shiro.dao.UserMapper;
import com.yang.shiro.entity.User;
import com.yang.shiro.util.SpringUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class JwtRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        if (jwtToken.getPrincipal() == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 从 JwtToken 中获取当前用户
        String phone = jwtToken.getPrincipal().toString();
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
        User user = userMapper.selectUserByPhone(phone);
        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(phone, jwtToken.getCredentials(), this.getName());
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}
