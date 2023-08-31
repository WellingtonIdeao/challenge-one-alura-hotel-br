package br.com.ideao.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import br.com.ideao.alurahotel.model.FormaPagamento;
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
			pstmt.setLong(4, reserva.getFormaPagmento().getId());
			
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

	public Reserva buscarPorId(Long id) {
	
		String sql = "SELECT R.id, R.data_entrada, R.data_saida, R.valor, F.id, F.nome FROM reserva R INNER JOIN "
		+" forma_pagamento F ON  F.id = R.forma_pagamento_id WHERE R.id = ?";
		
		Reserva reserva = null;
		
		try(PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
			pstmt.setLong(1, id);
			pstmt.execute();
			
			try(ResultSet rst = pstmt.getResultSet()){
				while(rst.next()) {
					FormaPagamento fp = new FormaPagamento(rst.getLong(5), rst.getString(6));
					
					LocalDate data_entrada = rst.getDate(2).toLocalDate();
					LocalDate data_saida = rst.getDate(3).toLocalDate();
					
					reserva = new Reserva(rst.getLong(1), data_entrada, data_saida, fp);
				}
			}		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reserva;	
	}

	public void alterar(Reserva reserva) {
		String sql = "UPDATE reserva SET data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento_id = ? WHERE id = ?";
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql)){
			pstmt.setString(1, reserva.getDataEntrada().toString());
			pstmt.setString(2, reserva.getDataSaida().toString());
			pstmt.setBigDecimal(3, reserva.getValor());
			pstmt.setLong(4, reserva.getFormaPagmento().getId());
			pstmt.setLong(5, reserva.getId());
			
			pstmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Long id) {
		try(PreparedStatement pstmt = this.connection.prepareStatement("DELETE FROM reserva WHERE id = ?")){
			pstmt.setLong(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
