package com.mdc.mq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {
	@Autowired
	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String receive(Destination destination) {
		TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
		String message = "";
		try {
			message = tm.getText();
			System.out.println("从队列" + destination.toString() + "收到了消息" + tm.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}
