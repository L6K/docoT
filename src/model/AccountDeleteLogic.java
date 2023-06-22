package model;

import dao.AccountDAO;

public class AccountDeleteLogic {

	public boolean execute(User a){
		AccountDAO dao = new AccountDAO();
		int result = dao.deleteAccount(a);
		return result == 1;
	}

}
