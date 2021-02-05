package com.project.TabernasSevilla.event;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.project.TabernasSevilla.domain.Message;
import com.project.TabernasSevilla.domain.NotificationMessage;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageListener implements PostInsertEventListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2194990078924947662L;
	
	private SimpMessagingTemplate simpMessagingTemplate;

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		final Object entity = event.getEntity();
		if(entity instanceof Message) {
			Message msg = (Message) entity;
			NotificationMessage notif = new NotificationMessage(msg.getMessage());
			simpMessagingTemplate.convertAndSendToUser(msg.getActor().getUser().getUsername(),"/secured/user/queue/specific-user",notif);
		}
	}

}
