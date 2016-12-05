package proxy.cglibproxy;

public class AccountProxyTest {
	public static void main(String[] args) {
		Account account = (Account) new AccountCglibProxyFactory().getInstance(new AccountImpl());
		account.queryAccount();
		account.updateAccount();
	}
}
