package net.xhalo.video.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public void sendMessage(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
