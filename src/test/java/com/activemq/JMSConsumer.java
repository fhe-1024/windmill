package com.activemq;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

	// 产品 仿糗事百科 那么审核是个问题了 主要需要一个爬虫
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();

		ConnectionFactory connectionFactory;
		Connection connection = null;

		Session session;
		Destination destination;
		MessageConsumer messageConsumer;

		connectionFactory = new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD,
				JMSConsumer.BROKEURL);
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("HelloWorld");
			messageConsumer = session.createConsumer(destination);
			while (true) {
				TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
				if (textMessage != null) {
					System.out.println("收到的消息:" + textMessage.getText());
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
