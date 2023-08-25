package br.com.ideao.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ideao.alurahotel.model.Nacionalidade;

public class NacionalidadeDao {
	private Connection connection;
	
	public NacionalidadeDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Nacionalidade> listar(){
		List<Nacionalidade> nacionalidades = new ArrayList<>();
		String sql = "SELECT N.id, N.nome FROM nacionalidade N";
		try(PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
			pstmt.execute();
			try (ResultSet rst = pstmt.getResultSet()) {
				while(rst.next()) {
					nacionalidades.add(new Nacionalidade(rst.getLong(1),rst.getString(2)));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return nacionalidades;
	}	
}
