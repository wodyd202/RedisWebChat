package com.ljy.chat.domain.chat;

import lombok.Getter;

@Getter
public class ChatMessage {

	private MessageType type;
	private String roomId;
	private String sender;
	private String payload;

	public void write(String message) {
		setPayload(message);
	}
	
	public ChatMessage(MessageType type, String roomId, String sender, String payload) {
		setType(type);
		setRoomId(roomId);
		setSender(sender);
		setPayload(payload);
	}

	private void setType(MessageType type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}
		this.type = type;
	}

	private void setRoomId(String roomId) {
		verfyIsNotEmpty(roomId);
		this.roomId = roomId;
	}

	private void setSender(String sender) {
		verfyIsNotEmpty(sender);
		this.sender = sender;
	}

	private void setPayload(String payload) {
		verfyIsNotEmpty(payload);
		this.payload = payload;
	}

	private void verfyIsNotEmpty(String value) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	public enum MessageType {
		ENTER, TALK
	}

	public boolean isEnterType() {
		return this.type == MessageType.ENTER;
	}
}
