package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.User;;

public class AccountDAO {
	// // 接続環境
	private final String url = "jdbc:mysql://localhost:3306/sukkiriShop?" + "useUnicode=true&characterEncoding=utf8";
	private final String user = "root";
	private final String password = "root";

	public Account findByLogin(User login) {
		Connection conn = null;
		Account account = null;

		try {
			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");
			// 接続
			conn = DriverManager.getConnection(url, user, password);
			// SELECT
			String sql = "SELECT USER_ID,PASS,MAIL,NAME,AGE FROM ACCOUNT WHERE USER_ID = ? AND PASS = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login.getName());
			ps.setString(2, login.getPass());

			// 実行
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");

				account = new Account(userId, pass, mail, name, age);
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
		}

		return account;
	}

//----------------------------------------------------------------------------------

	public int registerAccount(Account account) {
		Connection conn = null;
		int result= 0;

		try {
			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");
			// 接続
			conn = DriverManager.getConnection(url, user, password);

			// SELECT
			String sql = "INSERT INTO ACCOUNT (USER_ID,PASS,MAIL,NAME,AGE) VALUES (?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, account.getUserId());
			ps.setString(2, account.getPass());
			ps.setString(3, account.getMail());
			ps.setString(4, account.getName());
			ps.setInt(5, account.getAge());

			// 実行
			result = ps.executeUpdate();
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
		}

		return result;
	}
}
