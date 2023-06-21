package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;;

public class AccountDAO {
	// // 接続環境
	private final String url = "jdbc:mysql://localhost:3306/docotsubu?" + "useUnicode=true&characterEncoding=utf8";
	private final String user = "root";
	private final String password = "root";

	public User findByLogin(User login) {
		Connection conn = null;
		User account = null;

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

				account = new User(name, pass);
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

	public int registerAccount(User account) {
		Connection conn = null;
		int result= 0;

		try {
			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");
			// 接続
			conn = DriverManager.getConnection(url, user, password);

			// SELECT
			String sql = "INSERT INTO users (PASS,NAME) VALUES (?,?)";

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
//----------------------------------------------------------------------------------
	public int deleteAccount(User account){
		Connection conn = null;
		int result=0;
		try{
			// JDBCドライバを読み込み
			Class.forName("com.mysql.jdbc.Driver");
			// 接続
			conn = DriverManager.getConnection(url, user, password);

			// SELECT
			String sql = "DELETE FROM users WHERE NAME = ? AND PASS = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, account.getName());
			ps.setString(2, account.getPass());

			// 実行
			result = ps.executeUpdate();

		}catch (SQLException e) {
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
