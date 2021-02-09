package com.ljy.chat.domain.chat.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljy.chat.domain.room.ChatRoom;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat")
public class ChatContoller {
	private final ChatService chatService;

	@PostMapping("{roomName}")
	public ResponseEntity<ChatRoom> createRoom(@PathVariable String roomName) {
		return new ResponseEntity<>(chatService.createChatRoom(roomName), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ChatRoom>> findAllRoom() {
		return new ResponseEntity<>(chatService.findAllChatRoom(), HttpStatus.OK);
	}

}
