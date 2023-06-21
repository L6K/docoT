package test;

import dao.AccountDAO;
import model.User;

public class AccountDAOTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testFindByLogin1();
		testFindByLogin2();
	}
	public static void testFindByLogin1(){
		User login = new User("湊 雄輔","abcd");
		AccountDAO dao = new AccountDAO();
		User result = dao.findByLogin(login);
		if(result!=null&&
				result.getPass().equals("abcd")&&
				result.getName().equals("湊 雄輔")){
			System.out.println("testFindByLogin:成功しました");
		}else{
			System.out.println("testFindByLogin:失敗しました");
		}
	}

	public static void testFindByLogin2(){
		User login=new User("minato","abcde");
		AccountDAO dao = new AccountDAO();
		User result = dao.findByLogin(login);
		if(result==null){
			System.out.println("testFindByLogin2:成功しました");
		}else{
			System.out.println("testFindByLogin2:失敗しました");
		}

	}

}
