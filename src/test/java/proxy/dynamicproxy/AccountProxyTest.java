package proxy.dynamicproxy;

public class AccountProxyTest {
	public static void main(String[] args) {
		Account account = (Account) new AccountProxyFactory().bind(new AccountImpl());
		account.queryAccount();
		account.updateAccount();
	}
}
