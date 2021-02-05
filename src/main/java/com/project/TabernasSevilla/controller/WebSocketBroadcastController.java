package com.project.TabernasSevilla.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.TabernasSevilla.domain.ChatMessage;

@Controller
public class WebSocketBroadcastController {

	@GetMapping("/stomp-broadcast")
	public String getWebSocketBroadcast() {
		return "stomp/stomp-broadcast.html";
	}
	


	@MessageMapping("/broadcast")
	@SendTo("/topic/messages")
	public ChatMessage test(ChatMessage chatMessage) {
		return new ChatMessage(chatMessage.getFrom(),chatMessage.getText(),"ALL");
	}
	
}
