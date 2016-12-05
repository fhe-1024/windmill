package proxy.staticproxy;

public class AccountProxy implements Account {

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
