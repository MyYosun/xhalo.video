package net.xhalo.video.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private CusWebSocketHandler webSocketHandler;

    @Autowired
    @Qualifier(value = "cusWebSocketInterceptor")
    private HandshakeInterceptor handshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler, "/webSocket/handler")
                .addInterceptors(handshakeInterceptor)
                .setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(webSocketHandler, "/webSocket/sockJs/handler")
                .addInterceptors(handshakeInterceptor)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
