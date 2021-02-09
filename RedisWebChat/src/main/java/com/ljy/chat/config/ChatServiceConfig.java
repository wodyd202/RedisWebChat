package com.ljy.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.chat.domain.chat.service.ChatService;

@Configuration
public class ChatServiceConfig {
	
	@Autowired
	private ObjectMapper objMapper;
	
	@Bean
	public ChatService chatService() {
		return new ChatService(objMapper);
	}
}
