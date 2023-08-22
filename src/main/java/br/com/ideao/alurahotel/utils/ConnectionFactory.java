package br.com.ideao.alurahotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/hotel_alura?" +
			"useTimezone=true&serverTimezone","root", "dbsql123");
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		} 
	}
}
