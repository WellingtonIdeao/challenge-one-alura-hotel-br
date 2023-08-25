package br.com.ideao.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ideao.alurahotel.model.Hospede;

public class HospedeDao {
	private Connection connection;
	
	public HospedeDao(Connection connection) {
		this.connection = connection;
	}

	public Hospede cadastrar(Hospede hospede) {
		String sql = "INSERT INTO hospede (nome, sobrenome, nacionalidade_id, telefone, data_nascimento, reserva_id) " +
				"VALUES (?, ?, ?, ?, ?, ?)";

		try(PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, hospede.getNome());
			pstmt.setString(2, hospede.getSobreNome());
			pstmt.setLong(3, hospede.getNacionalidade().getId());
			pstmt.setString(4, hospede.getTelefone());
			pstmt.setString(5, hospede.getDataNascimento().toString());
			pstmt.setLong(6, hospede.getReserva().getId());
			
			pstmt.execute();
			
			try(ResultSet rst = pstmt.getGeneratedKeys()){
				while(rst.next()) {
					hospede.setId(rst.getLong(1));
				}
			}		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return hospede;
	}	
}
