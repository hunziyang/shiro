package com.yang.shiro.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro的配置
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建过滤器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/login/register","anon");
        filterChainDefinitionMap.put("/login/login","anon");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 安全管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("passwordRealm") Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    @Qualifier("passwordRealm")
    public Realm realm(@Value("${md5.iterations}")int iterations){
        SecurityRealm securityRealm =new SecurityRealm();
        // 采用MD5加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(iterations);
        securityRealm.setCredentialsMatcher(matcher);
        return securityRealm;
    }
}
