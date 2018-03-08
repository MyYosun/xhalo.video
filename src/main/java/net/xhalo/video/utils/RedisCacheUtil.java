package net.xhalo.video.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.cache.RedisCache;

public class RedisCacheUtil {
    @Autowired
    SimpleCacheManager redisCacheManager;

    public Object get(String cacheName, Object key) {
        if(!(redisCacheManager.getCache(cacheName) instanceof RedisCache))
            return null;
        RedisCache redisCache = (RedisCache) redisCacheManager.getCache(cacheName);
        return redisCache.get(key);
    }

    public boolean put(String cacheName, Object key, Object value) {
        if(null == key || null == value)
            return false;
        if(!(redisCacheManager.getCache(cacheName) instanceof RedisCache))
            return false;
        RedisCache redisCache = (RedisCache) redisCacheManager.getCache(cacheName);
        redisCache.put(key, value);
        return true;
    }

    public boolean delete(String cacheName, Object key) {
        if(null == key)
            return false;
        if(!(redisCacheManager.getCache(cacheName) instanceof RedisCache))
            return false;
        RedisCache redisCache = (RedisCache) redisCacheManager.getCache(cacheName);
        redisCache.evict(key);
        return true;
    }
}
