package br.com.ideao.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ideao.alurahotel.model.User;

public class UserDao {
	private Connection connection;
	
	public UserDao(Connection connection) {
		this.connection = connection;
	}
	
	public User buscar(String username) {
		String sql = "SELECT U.username, U.password FROM user U WHERE U.username = ?";
		User user =  null;
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.execute();

			try (ResultSet rst = pstmt.getResultSet()) {
				while (rst.next()) {
					user = new User(rst.getString(1), rst.getString(2));
				}
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return user;
	}

}
