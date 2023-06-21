package model;

import dao.AccountDAO;

public class AccountRegisterLogic {

	public boolean execute(User a) {
		AccountDAO dao = new AccountDAO();
		int result= dao.registerAccount(a);
		return result == 1;
	}

}
