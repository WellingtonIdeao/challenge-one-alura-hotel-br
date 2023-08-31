package br.com.ideao.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.ideao.alurahotel.model.Hospede;
import br.com.ideao.alurahotel.model.Nacionalidade;
import br.com.ideao.alurahotel.model.Reserva;

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

	public List<Hospede> buscarPorSobreNome(String sobreNome) {
		String sql = "SELECT H.id, H.nome, H.sobrenome, H.data_nascimento, H.telefone, H.reserva_id, N.id, N.nome "
					+ "FROM hospede H INNER JOIN nacionalidade N ON N.id = H.nacionalidade_id  WHERE H.sobrenome = ?";
		List<Hospede> hospedes = new ArrayList<>();
		
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql)){
			pstmt.setString(1, sobreNome);
			pstmt.execute();
			
			try (ResultSet rst = pstmt.getResultSet()){
				while(rst.next()) {
					LocalDate data_nasc = rst.getDate(4).toLocalDate();
					Nacionalidade nc = new Nacionalidade(rst.getLong(7), rst.getString(8));
					Reserva rs = new Reserva();
					rs.setId(rst.getLong(6));	
					Hospede  hospede = new Hospede(rst.getLong(1), rst.getString(2), rst.getString(3), data_nasc, nc, rst.getString(5), rs );
					hospedes.add(hospede);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return hospedes;
	}

	public void alterar(Hospede hospede) {
		String sql = "UPDATE hospede SET nome = ?, sobrenome = ?, nacionalidade_id = ?,"
				+ " telefone = ?, data_nascimento = ?, reserva_id = ? WHERE id = ?";
		
		try (PreparedStatement pstmt = this.connection.prepareStatement(sql)){
			pstmt.setString(1, hospede.getNome());
			pstmt.setString(2, hospede.getSobreNome());
			pstmt.setLong(3, hospede.getNacionalidade().getId());
			pstmt.setString(4, hospede.getTelefone());
			pstmt.setString(5, hospede.getDataNascimento().toString());
			pstmt.setLong(6, hospede.getReserva().getId());
			pstmt.setLong(7, hospede.getId());
			
			pstmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
}
