package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AccountProxyFactory implements InvocationHandler {

	private Object target;

	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		boolean flag = method.getDeclaringClass().getName().equals("java.lang.Object");
		if (!flag) {
			System.out.println("代理before");
		}
		Object result = method.invoke(this.target, args);
		if (!flag) {
			System.out.println("代理after");
		}
		return result;
	}

}
