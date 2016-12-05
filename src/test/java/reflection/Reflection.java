package reflection;

import org.junit.Test;

public class Reflection {
	@Test
	public void test1() {
		Class<?>[] cs = HelloWorld.class.getInterfaces();
		HelloWorld.class.getConstructors();
		try {
			HelloWorld.class.getConstructor(HelloWorld.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Class<?> c : cs) {
			System.out.println(c.getName());
		}
	}
}

interface Hello {
	public String hello() throws Exception;
}

interface HelloFather {
	public String world() throws Exception;
}

class Father implements HelloFather {

	@Override
	public String world() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("world");
		return "world";
	}

}

class HelloWorld extends Father implements Hello {

	@Override
	public String hello() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("HelloWorld");
		return "helloworld";
	}

}