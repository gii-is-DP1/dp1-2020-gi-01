package com.project.TabernasSevilla.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.TabernasSevilla.domain.Message;

@Controller
public class WebSocketBroadcastController {

	@GetMapping("/stomp-broadcast")
	public String getWebSocketBroadcast() {
		return "stomp/stomp-broadcast.html";
	}

	@MessageMapping("/broadcast")
	@SendTo("/topic/messages")
	public Message send(Message message) {
		return new Message(message.getActor(), message.getDeliveryDate(), message.getReadDate(), message.getMessage(),
				message.getUrl());
	}
}
