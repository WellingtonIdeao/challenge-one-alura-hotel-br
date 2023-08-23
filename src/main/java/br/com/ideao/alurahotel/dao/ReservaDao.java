package br.com.ideao.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ideao.alurahotel.model.Reserva;

public class ReservaDao {
	private Connection connection;
	
	public ReservaDao(Connection connection) {
		this.connection = connection;
	}
	
	public Reserva cadastrar(Reserva reserva) {
		String sql = "INSERT INTO reserva (data_entrada, data_saida, valor, forma_pagamento_id) " +
						"VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, reserva.getDataEntrada().toString());
			pstmt.setString(2, reserva.getDataSaida().toString());
			pstmt.setBigDecimal(3, reserva.getValor());
			pstmt.setLong(4, reserva.getFormatoPagmentoId());
			
			pstmt.execute();
			
			try(ResultSet rst = pstmt.getGeneratedKeys()){
				while(rst.next()) {
					reserva.setId(rst.getLong(1));
				}
			}		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reserva;	
	}
}
