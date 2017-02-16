package springtest;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mdc.mq.MessageReceiver;
import com.mdc.mq.MessageSender;
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
		messageSender.sendMessage(queueDestination2, "上学时我在外面租的房子住，有个假期我爸妈来我这里玩。刚好前几天我被一女同学撞了下鼻子，晚上我特么就流鼻血了，给床上敷了好多，红点斑斑的。他们看见后一脸坏笑的问我是不是交女票了！我说没有啊！他们听后语重心长的说，放心吧，爸妈都是过来人思想不迂腐。支持你们小青年的生活，但是要讲卫生啊！听的我懵了！我解释都解释不清楚，越解释越说不清！他俩临走还说以后每个月多给我加1000生活费，听到这个，我顿时眼睛放光！一定要请那个女生吃一顿饭！等下次我爸妈来，再告诉他们，怀孕了，要钱上学时我在外面租的房子住，有个假期我爸妈来我这里玩。刚好前几天我被一女同学撞了下鼻子，晚上我特么就流鼻血了，给床上敷了好多，红点斑斑的。他们看见后一脸坏笑的问我是不是交女票了！我说没有啊！他们听后语重心长的说，放心吧，爸妈都是过来人思想不迂腐。支持你们小青年的生活，但是要讲卫生啊！听的我懵了！我解释都解释不清楚，越解释越说不清！他俩临走还说以后每个月多给我加1000生活费，听到这个，我顿时眼睛放光！一定要请那个女生吃一顿饭！等下次我爸妈来，再告诉他们，怀孕了，要钱上学时我在外面租的房子住，有个假期我爸妈来我这里玩。刚好前几天我被一女同学撞了下鼻子，晚上我特么就流鼻血了，给床上敷了好多，红点斑斑的。他们看见后一脸坏笑的问我是不是交女票了！我说没有啊！他们听后语重心长的说，放心吧，爸妈都是过来人思想不迂腐。支持你们小青年的生活，但是要讲卫生啊！听的我懵了！我解释都解释不清楚，越解释越说不清！他俩临走还说以后每个月多给我加1000生活费，听到这个，我顿时眼睛放光！一定要请那个女生吃一顿饭！等下次我爸妈来，再告诉他们，怀孕了，要钱上学时我在外面租的房子住，有个假期我爸妈来我这里玩。刚好前几天我被一女同学撞了下鼻子，晚上我特么就流鼻血了，给床上敷了好多，红点斑斑的。他们看见后一脸坏笑的问我是不是交女票了！我说没有啊！他们听后语重心长的说，放心吧，爸妈都是过来人思想不迂腐。支持你们小青年的生活，但是要讲卫生啊！听的我懵了！我解释都解释不清楚，越解释越说不清！他俩临走还说以后每个月多给我加1000生活费，听到这个，我顿时眼睛放光！一定要请那个女生吃一顿饭！等下次我爸妈来，再告诉他们，怀孕了，要钱");
	}


	@Test
	public void testTopicProvider() {
		topicProvider.publish(topicDestination, "我是发布者");
	}

}
