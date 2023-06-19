package test;

import dao.AccountDAO;
import model.Account;
import model.Login;

public class AccountDAOTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testFindByLogin1();
		testFindByLogin2();
	}
	public static void testFindByLogin1(){
		Login login = new Login("minato","1234");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result!=null&&result.getUserId().equals("minato")&&
				result.getPass().equals("1234")&&
				result.getMail().equals("minato@sukkiri.com")&&
				result.getName().equals("湊 大輔")&&
				result.getAge()==23){
			System.out.println("testFindByLogin:成功しました");
		}else{
			System.out.println("testFindByLogin:失敗しました");
		}
	}

	public static void testFindByLogin2(){
		Login login=new Login("minato","12345");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		if(result==null){
			System.out.println("testFindByLogin2:成功しました");
		}else{
			System.out.println("testFindByLogin2:失敗しました");
		}

	}

}
