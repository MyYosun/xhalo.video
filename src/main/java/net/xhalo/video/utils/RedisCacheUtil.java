package net.xhalo.video.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheUtil {
    @Autowired
    SimpleCacheManager redisCacheManager;
    private Logger logger = LoggerFactory.getLogger(RedisCacheUtil.class);

    public Object get(String cacheName, Object key) {
        if (!(redisCacheManager.getCache(cacheName) instanceof RedisCache))
            return null;
        RedisCache redisCache = (RedisCache) redisCacheManager.getCache(cacheName);
        try {
            Object result = redisCache.get(key).get();
            return result;
        } catch (Throwable throwable) {
            logger.error("CACHE GET FAIL:", throwable);
        }
        return null;
    }

    public boolean put(String cacheName, Object key, Object value) {
        if (null == key || null == value)
            return false;
        if (!(redisCacheManager.getCache(cacheName) instanceof RedisCache))
            return false;
        RedisCache redisCache = (RedisCache) redisCacheManager.getCache(cacheName);
        redisCache.put(key, value);
        return true;
    }

    public boolean delete(String cacheName, Object key) {
        if (null == key)
            return false;
        if (!(redisCacheManager.getCache(cacheName) instanceof RedisCache))
            return false;
        RedisCache redisCache = (RedisCache) redisCacheManager.getCache(cacheName);
        redisCache.evict(key);
        return true;
    }

    public boolean clearCache(String cacheName) {
        if (null == cacheName)
            return false;
        if (!(redisCacheManager.getCache(cacheName) instanceof RedisCache))
            return false;
        RedisCache redisCache = (RedisCache) redisCacheManager.getCache(cacheName);
        redisCache.clear();
        return true;
    }
}
