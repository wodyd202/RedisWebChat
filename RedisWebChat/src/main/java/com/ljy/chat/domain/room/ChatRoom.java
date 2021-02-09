package com.ljy.chat.domain.room;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.ljy.chat.domain.chat.ChatMessage;
import com.ljy.chat.domain.chat.service.ChatService;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {
	private String roomId;
	private String name;
	private Set<WebSocketSession> userSesions = new HashSet<>();

	@Builder
	public ChatRoom(String roomId, String name) {
		this.roomId = roomId;
		this.name = name;
	}

	public void handleActions(WebSocketSession userSession, ChatMessage message, ChatService service) {
		if (isEnter(message)) {
			userSesions.add(userSession);
			message.write(message.getSender() + " 님이 입장했습니다.");
		}
		sendMessage(message,service);
	}

	private <T> void sendMessage(T message, ChatService service) {
		userSesions.parallelStream().forEach(user -> service.sendMessage(user, message));
	}

	private boolean isEnter(ChatMessage message) {
		return message.isEnterType();
	}
}
