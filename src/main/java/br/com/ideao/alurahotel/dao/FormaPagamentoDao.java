package br.com.ideao.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ideao.alurahotel.model.FormaPagamento;

public class FormaPagamentoDao {
	private Connection connection;
	
	public FormaPagamentoDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<FormaPagamento> listar(){
		List<FormaPagamento> formasPagamentos = new ArrayList<>();
		String sql = "SELECT F.id, F.nome FROM forma_pagamento F";
		try(PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
			pstmt.execute();
			try (ResultSet rst = pstmt.getResultSet()) {
				while(rst.next()) {
					formasPagamentos.add(new FormaPagamento(rst.getLong(1),rst.getString(2)));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return formasPagamentos;
	}

}
