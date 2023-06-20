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
	private final String url = "jdbc:mysql://localhost:3306/docotsubu?" + "useUnicode=true&characterEncoding=utf8";
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
			String sql = "SELECT PASS,NAME FROM users WHERE NAME = ? AND PASS = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, login.getName());
			ps.setString(2, login.getPass());

			// 実行
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String pass = rs.getString("PASS");
				String name = rs.getString("NAME");

				account = new Account(pass, name);
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
			String sql = "INSERT INTO ACCOUNT (PASS,NAME) VALUES (?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, account.getPass());
			ps.setString(2, account.getName());

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
