package br.com.ideao.alurahotel.utils;

import java.sql.Connection;

public class JdbcUtil {
	
	 private static final ConnectionFactory FACTORY = new ConnectionFactory();
	 
	 public static Connection getConnection() {
		 return FACTORY.getConnection();
	 }

}
