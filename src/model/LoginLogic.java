package model;

import dao.AccountDAO;

public class LoginLogic {
	public boolean execute(User login){
		AccountDAO dao = new AccountDAO();
		User account = dao.findByLogin(login);


		return account != null;

	}
}

//	public boolean execute(User user){
//		if(user.getPass().equals("1234")){
//			return true;
//		}
//			return false;
//		}
//}
