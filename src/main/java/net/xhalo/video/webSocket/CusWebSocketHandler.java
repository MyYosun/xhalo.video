package net.xhalo.video.webSocket;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static net.xhalo.video.config.ConstantProperties.CLIENT_ID;
import static net.xhalo.video.config.MaginNumberProperties.NUM_ZERO;

@Component
public class CusWebSocketHandler extends TextWebSocketHandler {
    //在线的用户列表
    private static final Map<String, WebSocketSession> users;

    private static Logger logger = LoggerFactory.getLogger(CusWebSocketHandler.class);

    static {
        users = new HashMap<String, WebSocketSession>();
    }

    private String getClientUsername(WebSocketSession socketSession) {
        String clientUsername = (String) socketSession.getAttributes().get(CLIENT_ID);
        return clientUsername;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String clientUsername = getClientUsername(session);
        if (StringUtils.isEmpty(clientUsername)) {
            logger.error("WebSocket: Error Get Client Username");
            return;
        }
        users.put(clientUsername, session);
        logger.info("WebSocket: {} connect Success!", clientUsername);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String clientUsername = getClientUsername(session);
        users.remove(clientUsername);
        logger.info("WebSocket: {}'s connection closed!", clientUsername);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //do something

    }

    public boolean sendMessageToClient(String clientUsername, TextMessage message) {
        WebSocketSession session = users.get(clientUsername);
        if (null == session) {
            logger.error("WebSocket: Send Message To Client {} Error, Cannot Find Client User Connect!", clientUsername);
            return false;
        }
        if (!session.isOpen()) {
            logger.error("WebSocket: Send Message To Client {} Error, Connection Closed!", clientUsername);
            return false;
        }
        try {
            session.sendMessage(message);
            return true;
        } catch (IOException e) {
            logger.error("WebSocket: Send Message To Client {} Error: {}", clientUsername, e);
            try {
                handleTransportError(session, e);
            } catch (Exception e1) {
                logger.error("WebSocket: Error", e1);
            }
            return false;
        }
    }

    public boolean sendMessageToAll(TextMessage message) {
        Set<String> usernameSet = users.keySet();
        Integer count = NUM_ZERO;
        for (String username : usernameSet) {
            if (sendMessageToClient(username, message)) {
                count++;
            }
        }
        logger.info("WebSocket: Total {} Clients, Send Success {} Clients", usernameSet.size(), count);
        return true;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (!session.isOpen()) {
            session.close();
            users.remove(getClientUsername(session));
        }
    }
}
