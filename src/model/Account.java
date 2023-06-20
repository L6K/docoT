package model;

public class Account {
	private String pass;
	private String name;

	public Account(String pass,String name){
		this.pass=pass;
		this.name=name;
	}
	public String getPass(){
		return pass;
	}
	public String getName(){
		return name;
	}
}
