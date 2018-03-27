package net.xhalo.video.task;

import net.xhalo.video.utils.RedisCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RedisTask {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    private Logger logger = LoggerFactory.getLogger(RedisTask.class);

    //每隔5分钟清理一次video的cache
    @Scheduled(cron = "0 0/5 * * * ?")
    public void clearVideoCache() {
        redisCacheUtil.clearCache("video");
        logger.info("SCHEDULED CLEAR VIDEO CACHE SUCCESS: EVERY FIVE MINUTES.");
    }

    //每隔12小时清理一次category的cache
    @Scheduled(cron = "0 0 0/12 * * ?")
    public void clearCategoryCache() {
        redisCacheUtil.clearCache("category");
        logger.info("SCHEDULED CLEAR CATEGORY CACHE SUCCESS: EVERY TWELVE HOURS.");
    }

}
