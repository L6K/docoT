package model;

import dao.AccountDAO;

public class AccountRegisterLogic {

	public boolean execute(Account a) {
		AccountDAO dao = new AccountDAO();
		int result= dao.registerAccount(a);
		return result == 1;
	}

}
