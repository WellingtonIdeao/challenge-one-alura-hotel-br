package br.com.ideao.alurahotel.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

class ConnectionFactory {
	
	private DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimezone=true&serverTimezone");
		cpds.setUser("root");
		cpds.setPassword("dbsql123");
		
		cpds.setMinPoolSize(3);
		cpds.setMaxPoolSize(100);
		cpds.setMaxIdleTime(300);
		cpds.setMaxStatements(50);
		cpds.setIdleConnectionTestPeriod(120);
		
		this.dataSource = cpds;
	}

	public Connection getConnection() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		} 
	}
}
