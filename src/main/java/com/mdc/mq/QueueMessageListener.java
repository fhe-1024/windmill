package com.mdc.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("收到文本消息" + tm.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
