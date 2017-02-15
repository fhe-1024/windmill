package springtest;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mdc.mq.MessageReceiver;
import com.mdc.mq.MessageSender;
import com.mdc.mq.TopicMessageListener;
import com.mdc.mq.TopicProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SpringTest {

	@Autowired
	private Destination queueDestination;
	@Autowired
	private Destination queueDestination2;

	@Autowired
	private MessageSender messageSender;
	@Autowired
	private MessageReceiver messageReceiver;

	@Autowired
	private Destination topicDestination;

	@Autowired
	private TopicProvider topicProvider;

	@Test
	public void testSender() {
		messageSender.sendMessage(queueDestination2, "我是狗");
	}

	@Test
	public void testReceiver() {
		messageReceiver.receive(queueDestination);
	}

	@Test
	public void testTopicProvider() {
		topicProvider.publish(topicDestination, "我是发布者");
	}

}
