package model;

import dao.MutterDAO;

public class AllTweetCleanLogic {

	public boolean execute(User a){
		MutterDAO dao = new MutterDAO();
		boolean result = dao.allTweetClean(a);
		return result;

	}

}
