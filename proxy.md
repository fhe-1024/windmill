##代理模式

* 什么是代理模式  
	代理模式的作用是：为其他对象提供一种代理以控制对这个对象的访问。
* 代理模式有什么好处  
	在某些情况下，一个客户不想或不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用
* 代理模式一般涉及到的角色有：  
	抽象角色：声明真实对象和代理对象的共同接口，这样一来在任何可以使用目标对象的地方都可以使用代理对象。    
	代理角色：代理对象内部含有目标对象的引用，从而可以在任何时候操作目标对象；代理对象提供一个与目标对象相同的接口，以便可以在任何时候替代目标对象。代理对象通常在客户端调用传递给目标对象之前或之后，执行某个操作，而不是单纯的将调用传递给目标对象，同时，代理对象可以在执行真实对象操作时，附加其他的操作，相当于对真实对象进行封装。  
	真实角色：定义了代理对象所代表的目标对象，代理角色所代表的真实对象，是我们最终要引用的对象，定义了代理对象所代表的目标对象。


####静态代理
抽象角色  
>      public interface Account {
	        public void queryAccount();
	         public void updateAccount();
	   } 
 
真实角色  
>     public class AccountImpl implements Account {
			@Override
			public void queryAccount() {
				// TODO Auto-generated method stub
				System.out.println("查看账户");
			}
			@Override
			public void updateAccount() {
				// TODO Auto-generated method stub
				System.out.println("修改账户");
			}
     }

代理角色  
>     public class AccountProxy implements Account {
		private Account account;
		public AccountProxy(Account account) {
			super();
			this.account = account;
		}
		@Override
		public void queryAccount() {
			// TODO Auto-generated method stub
			System.out.println("代理before");
			account.queryAccount();
			System.out.println("代理after");
		}
		@Override
		public void updateAccount() {
			// TODO Auto-generated method stub
			System.out.println("代理before");
			account.updateAccount();
			System.out.println("代理after");
		}
	}

Demo  
>     public class AccountProxyTest {
		public static void main(String[] args) {
			// AccountProxy为自己实现的dialing类，可以发现，一个代理类只能为一个接口服务
			Account account = new AccountImpl();
			AccountProxy proxy = new AccountProxy(account);
			proxy.queryAccount();
			proxy.updateAccount();
		}
	}
####动态代理
代理类  
>      public class AccountProxyFactory implements InvocationHandler {
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

Demo  
>     public class AccountProxyTest {
		public static void main(String[] args) {
			Account account = (Account) new AccountProxyFactory().bind(new AccountImpl());
			account.queryAccount();
			account.updateAccount();
		}
	}

####cglib代理
代理类  
>      public class AccountCglibProxyFactory implements MethodInterceptor {
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

Demo  
>     public class AccountProxyTest {
		public static void main(String[] args) {
			Account account = (Account) new AccountCglibProxyFactory().getInstance(new AccountImpl());
			account.queryAccount();
			account.updateAccount();
		}
    }

