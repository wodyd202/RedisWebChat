package com.ljy.chat.domain.chat.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.chat.domain.chat.ChatMessage;
import com.ljy.chat.domain.chat.service.ChatService;
import com.ljy.chat.domain.room.ChatRoom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

	private final ObjectMapper objMapper;
	private final ChatService service;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload : {}", payload);
		
		ChatMessage chatMessage = objMapper.readValue(payload, ChatMessage.class);
		ChatRoom room = service.findChatRoomById(chatMessage.getRoomId());
		room.handleActions(session, chatMessage, service);
	}
}
