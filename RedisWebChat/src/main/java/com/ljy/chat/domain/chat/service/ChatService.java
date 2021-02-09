package com.ljy.chat.domain.chat.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.chat.domain.room.ChatRoom;

public class ChatService {
	private final ObjectMapper objMapper;
	private final Map<String, ChatRoom> chatRooms;

	public List<ChatRoom> findAllChatRoom(){
		return new ArrayList<>(chatRooms.values());
	}
	
	public ChatRoom findChatRoomById(String roomId) {
		return chatRooms.get(roomId);
	}
	
	public ChatRoom createChatRoom(String name) {
		String roomId = UUID.randomUUID().toString();
		ChatRoom room = ChatRoom.builder()
						.name(name)
						.roomId(roomId)
						.build();
		chatRooms.put(roomId, room);
		return room;
	}
	
	public <T> void sendMessage(WebSocketSession userSession,T message) {
		try {
			userSession.sendMessage(new TextMessage(objMapper.writeValueAsBytes(message)));
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public ChatService(ObjectMapper objMapper) {
		this.chatRooms = new HashMap<>();
		this.objMapper = objMapper;
	}
}
