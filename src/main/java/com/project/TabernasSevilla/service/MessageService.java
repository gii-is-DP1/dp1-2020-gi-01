package com.project.TabernasSevilla.service;

import java.time.Instant;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.TabernasSevilla.domain.Actor;
import com.project.TabernasSevilla.domain.Message;
import com.project.TabernasSevilla.domain.RestaurantOrder;
import com.project.TabernasSevilla.repository.MessageRepository;

@Service
@Transactional
public class MessageService {

	private MessageRepository msgRepo;
	
	private ActorService actorService;
	
	@Autowired
	public MessageService(MessageRepository msgRepo, ActorService actorService) {
		super();
		this.msgRepo = msgRepo;
		this.actorService = actorService;
	}
	
	public void delete(Message msg) {
		this.msgRepo.delete(msg);
	}
	
	public Message save(Message msg) {
		return this.msgRepo.save(msg);
	}
	
	public Message create() {
		return new Message();
	}
	
	public Message sendOrderUpdate(RestaurantOrder order) {
		Message msg = create();
		msg.setActor(order.getActor());
		msg.setUrl("/order/"+order.getId()+"/view");
		msg.setMessage("Your order has been updated: "+order.getStatus());
		msg.setDeliveryDate(Instant.now());
		return save(msg);
	}
	
	public Page<Message> findLatestReadByActor(){
		Actor actor = this.actorService.getPrincipal();
		return this.msgRepo.findLatestReadByActor(actor.getId(), PageRequest.of(0, 5));
	}
	
	public List<Message> findUnreadByActor(Actor actor){
		return this.msgRepo.findUnreadByActor(actor.getId());
	}
}
