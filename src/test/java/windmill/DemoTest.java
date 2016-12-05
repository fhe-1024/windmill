package windmill;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class DemoTest {

	private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			// TODO Auto-generated method stub
			return super.initialValue();
		}
	};

	@Test
	public void test1() {
		Map<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();
		concurrentHashMap.put("test", "hello");
		concurrentHashMap.put("formal", "world");
		System.out.println(concurrentHashMap.get("formal"));
		Iterator<Entry<String, Object>> it = concurrentHashMap.entrySet().iterator();
		while(it.hasNext()){
			concurrentHashMap.remove(it.next().getKey());
		}
	}

	@Test
	public void test2() {
		Map<String, Object> concurrentHashMap = new HashMap<String, Object>();
		concurrentHashMap.put("test", "hello");
		concurrentHashMap.put("formal", "world");
		System.out.println(concurrentHashMap.get("formal"));
		Iterator<Entry<String, Object>> it = concurrentHashMap.entrySet().iterator();
		while(it.hasNext()){
			concurrentHashMap.remove(it.next().getKey());
		}
	}
	@Test
	public void test3(){
		System.out.println(DemoTest.class.isPrimitive());
	}
}
