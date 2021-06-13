package com.yang.shiro.config.shiro.jwt;

import com.yang.shiro.dao.PermMapper;
import com.yang.shiro.dao.RoleMapper;
import com.yang.shiro.dao.UserMapper;
import com.yang.shiro.entity.Perm;
import com.yang.shiro.entity.Role;
import com.yang.shiro.entity.User;
import com.yang.shiro.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String phone = (String) principalCollection.getPrimaryPrincipal();
        if (!StringUtils.isEmpty(phone)) {
            RoleMapper roleMapper = SpringUtil.getBean(RoleMapper.class);
            PermMapper permMapper = SpringUtil.getBean(PermMapper.class);
            Set<Perm> perms = permMapper.selectPremByPhone(phone);
            Set<Role> roles = roleMapper.selectRolesByPhone(phone);
            List<String> rolesList = roles.stream().filter(role -> role != null).map(Role::getRoleKey).collect(Collectors.toList());
            List<String> permList = perms.stream().filter(perm -> perm != null).map(Perm::getPermKey).collect(Collectors.toList());
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            if (rolesList.size() > 0) {
                info.addRoles(rolesList);
            }
            if (permList.size() > 0) {
                info.addStringPermissions(permList);
            }
            return info;
        }
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
