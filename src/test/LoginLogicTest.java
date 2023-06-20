package test;

import model.LoginLogic;
import model.User;

public class LoginLogicTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testExecute1();
		testExecute2();
	}
	public static void testExecute1(){
		User login = new User("minato","1234");
		LoginLogic bo = new LoginLogic();
		boolean result =bo.execute(login);
		if(!result){
			System.out.println("testExecute1:成功しました");
		}else{
			System.out.println("testExecute1:失敗しました");
		}
	}
	public static void testExecute2(){
		User login = new User("minato","12345");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if(!result){
			System.out.println("testExecute2:成功しました");
		}else{
			System.out.println("testExecute2:失敗しました");
		}
	}

}
