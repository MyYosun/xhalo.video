package net.xhalo.video.task;

import net.xhalo.video.utils.DateUtil;
import net.xhalo.video.webSocket.CusWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static net.xhalo.video.config.MaginNumberProperties.*;

@Component
public class MessageTask {
    @Autowired
    private CusWebSocketHandler cusWebSocketHandler;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void sendMsg() {
        StringBuffer scheduledMsg = new StringBuffer();
        scheduledMsg.append("你好，");
        scheduledMsg.append("现在是");
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
        scheduledMsg.append(dateFormat.format(now));
        scheduledMsg.append(",");
        scheduledMsg.append(createMsgFromHour(now));
        cusWebSocketHandler.sendMessageToAll(new TextMessage(scheduledMsg));
    }

    private String createMsgFromHour(Date now) {
        Integer nowHour = DateUtil.getHour(now);
        if (nowHour == NUM_ZERO) {
            return "新的一天，xhalo-video依旧陪伴你，早点睡哦~";
        } else if (nowHour >= NUM_ONE && nowHour < NUM_FIVE) {
            return "凌晨的你看起来很美，如果能早睡，那你会更美丽~";
        } else if (nowHour >= NUM_FIVE && nowHour < NUM_NINE) {
            return "早上好，早起的人总是让人赏心悦目~";
        } else if (nowHour >= NUM_NINE && nowHour < NUM_TWELEVE) {
            return "早上好，新的一天希望你有个好心情~";
        } else if (nowHour >= NUM_TWELEVE && nowHour < NUM_FOURTEN) {
            return "中午好，睡个午觉会给你的一天增添新的激情~";
        } else if (nowHour >= NUM_FOURTEN && nowHour < NUM_SEVENTEN) {
            return "下午好，喝杯下午茶吧~";
        } else if (nowHour >= NUM_SEVENTEN && nowHour < NUM_NINETEN) {
            return "傍晚好，出去散散步，享受一天最悠闲最美的时刻吧~";
        } else if (nowHour >= NUM_NINETEN && nowHour < NUM_TWENTY_TWO) {
            return "晚上好，吃完晚饭的你是不是需要一些娱乐呢，欢迎观赏~";
        } else if (nowHour >= NUM_TWENTY_TWO && nowHour <= NUM_TWENTY_THREE) {
            return "深夜了，你是不是因为什么而睡不着，早点睡觉咯~";
        }
        return null;
    }
}
