package com.mdc.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("TopicMessageListener1 \t" + tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
