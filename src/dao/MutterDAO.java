package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;


public class MutterDAO {

	String url = "jdbc:mysql://localhost:3306/docotsubu?useUnicode=true&characterEncoding=utf8";
	String user = "root";
	String password = "root";

	public List<Mutter> findAll() {

		// returnするリストを用意
		List<Mutter> mutterList = new ArrayList<>();

		Connection conn = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, password);

			String sql = "SELECT ID , NAME , TEXT FROM MUTTER ORDER BY ID DESC";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String text = rs.getString("TEXT");

				Mutter mutter = new Mutter( id , name , text);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // try

		return mutterList;

	}//findAll

	//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	public boolean create(Mutter mutter){

		Connection conn = null;

		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, password);

			//プレースホルダによるSQL分の組み立て
			//SQL文の中にクエスチョンマーク（？）の形でパラメータを埋め込んで仮のSQL文を作る
			//利点・・・SQL文の見易さ・不正な検索を防止
			String sql = "INSERT INTO MUTTER(NAME , TEXT) VALUES(?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			//SQLの文にあった(？)の位置に値を入れる
			//第一引数・・・  (？)の場所   , 第二引数・・・値
			ps.setString( 1 , mutter.getUserName() );
			ps.setString( 2 , mutter.getText() );

			//SELECT文の際に使用したexecuteQuery()の戻り値は
			//検索の結果が格納されているResultSetオブジェクト
			//executeUpdate()の戻り値は更新した行数(int)
			//INSERT文，UPDATE文，DELETE文の際に使用
			int result = ps.executeUpdate();

			//１行更新できたら
			if(result == 1){
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // try

		return false;

	}//create
}//class