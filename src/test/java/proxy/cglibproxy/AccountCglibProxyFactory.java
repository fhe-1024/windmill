package proxy.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AccountCglibProxyFactory implements MethodInterceptor {

	private Object target;

	public Object getInstance(Object target) {
		this.target = target;
		return Enhancer.create(this.target.getClass(), this);
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub
		boolean objFlag = method.getDeclaringClass().getName().equals("java.lang.Object");
		if (!objFlag) {
			System.out.println("before");
		}
		Object result = null;
		result = proxy.invokeSuper(obj, args);
		if (!objFlag) {
			System.out.println("after");
		}
		return result;
	}

}
