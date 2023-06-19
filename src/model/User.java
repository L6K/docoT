package model;

import java.io.Serializable;

public class User implements Serializable {

	// フィールド
	private String name;	// ユーザー名
	private String pass;	// パスワード

	// メソッド
	// コンストラクタ
	public User(){}

	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public String getName() {
		return this.name;
	}
	public String getPass() {
		return this.pass;
	}

}
