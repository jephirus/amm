package com.dabizi.amm.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;  
import org.springframework.web.socket.WebSocketSession;  
import org.springframework.web.socket.handler.TextWebSocketHandler;  
  
// 暂时不用，用SystemWebSocketHandler代替。要在applicationContext.xml中配置。
@Service
public class WebsocketEndPoint extends TextWebSocketHandler {
  
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");  
        session.sendMessage(returnMessage);  
    }  
}