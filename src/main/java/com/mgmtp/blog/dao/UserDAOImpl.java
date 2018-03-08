package com.mgmtp.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mgmtp.blog.dao.UserDAO;
import com.mgmtp.blog.model.User;

public class UserDAOImpl implements UserDAO
{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(User user){

		String sql = "INSERT INTO Users " +
				"(id, username, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			//

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public User findByUsername(String username){

		String sql = "SELECT * FROM Users WHERE username = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			User user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(
					rs.getLong("id"),
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("firstname"),
					rs.getString("lastname")
				);
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}