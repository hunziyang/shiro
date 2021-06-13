package com.yang.shiro.config.shiro;

import com.yang.shiro.dao.PermMapper;
import com.yang.shiro.dao.RoleMapper;
import com.yang.shiro.dao.UserMapper;
import com.yang.shiro.entity.Perm;
import com.yang.shiro.entity.Role;
import com.yang.shiro.entity.User;
import com.yang.shiro.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityRealm extends AuthorizingRealm {
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
        String phone = (String) authenticationToken.getPrincipal();
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
        User user = userMapper.selectUserByPhone(phone);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
