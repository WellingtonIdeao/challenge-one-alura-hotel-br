package br.com.ideao.alurahotel.controller;

import java.sql.Connection;
import java.util.List;

import br.com.ideao.alurahotel.dao.NacionalidadeDao;
import br.com.ideao.alurahotel.model.Nacionalidade;
import br.com.ideao.alurahotel.utils.JdbcUtil;

public class NacionalidadeController {
	private NacionalidadeDao nacionalidadeDao;
	
	public NacionalidadeController() {
		Connection connection = JdbcUtil.getConnection();
		this.nacionalidadeDao = new NacionalidadeDao(connection);
	}
	
	public List<Nacionalidade> listar(){
		return this.nacionalidadeDao.listar();
	}
	
}
