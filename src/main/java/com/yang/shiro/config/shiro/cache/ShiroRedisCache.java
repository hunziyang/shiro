package com.yang.shiro.config.shiro.cache;

import com.alibaba.fastjson.JSON;
import com.yang.shiro.util.SpringUtil;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private String key;

    public ShiroRedisCache(String key) {
        this.key = key;
    }

    @Override
    public V get(K k) throws CacheException {
        RedisTemplate redisTemplate = SpringUtil.getBean("defaultRedisTemplate");
        try {
            Object rawValue = redisTemplate.opsForHash().get(key,k.toString());
            String s = JSON.toJSONString(rawValue);
            SimpleAuthorizationInfo simpleAuthenticationInfo= JSON.parseObject(s, SimpleAuthorizationInfo.class);
            V value = (V) simpleAuthenticationInfo;
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        RedisTemplate redisTemplate = SpringUtil.getBean("defaultRedisTemplate");
        if (ObjectUtils.isEmpty(k) || ObjectUtils.isEmpty(v)) {
            return null;
        } else {
            redisTemplate.opsForHash().put(key,k.toString(),v);
            redisTemplate.expire(key,1, TimeUnit.DAYS);
        }
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        RedisTemplate redisTemplate = SpringUtil.getBean("defaultRedisTemplate");
        redisTemplate.opsForHash().delete(key,k.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {
        RedisTemplate redisTemplate = SpringUtil.getBean("defaultRedisTemplate");
        redisTemplate.delete(key);
    }

    @Override
    public int size() {
        RedisTemplate redisTemplate = SpringUtil.getBean("defaultRedisTemplate");
        return redisTemplate.opsForHash().size(key).intValue();
    }

    @Override
    public Set<K> keys() {
        RedisTemplate redisTemplate = SpringUtil.getBean("defaultRedisTemplate");
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Collection<V> values() {
        RedisTemplate redisTemplate = SpringUtil.getBean("defaultRedisTemplate");
        return redisTemplate.opsForHash().values(key);
    }
}
