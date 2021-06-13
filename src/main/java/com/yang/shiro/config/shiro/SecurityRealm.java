package com.yang.shiro.config.shiro;

import com.yang.shiro.dao.UserMapper;
import com.yang.shiro.entity.User;
import com.yang.shiro.util.SpringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;


public class SecurityRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String phone = (String) authenticationToken.getPrincipal();
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
        User user = userMapper.selectUserByPhone(phone);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
