package com.mdc.mq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class TopicProvider {
	@Autowired
	private JmsTemplate topicJmsTemplate;

	/**
	 * 向指定的topic发布消息
	 * 
	 * @param topic
	 * @param msg
	 */
	public void publish(final Destination topic, final String msg) {
		topicJmsTemplate.send(topic, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				System.out.println("topic name 是" + topic.toString() + "，发布消息内容为:\t" + msg);
				return session.createTextMessage(msg);
			}
		});
	}

	public void setTopicJmsTemplate(JmsTemplate topicJmsTemplate) {
		this.topicJmsTemplate = topicJmsTemplate;
	}
}
