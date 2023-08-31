package br.com.ideao.alurahotel.controller;

import java.sql.Connection;
import java.util.List;

import br.com.ideao.alurahotel.dao.HospedeDao;
import br.com.ideao.alurahotel.model.Hospede;
import br.com.ideao.alurahotel.utils.JdbcUtil;

public class HospedeController {
	private HospedeDao hospedeDao;
		
	public HospedeController() {
			Connection connection = JdbcUtil.getConnection();
			this.hospedeDao = new HospedeDao(connection);
	}
		
	public Hospede cadastrar(Hospede hospede) {
		return this.hospedeDao.cadastrar(hospede);	
	}
	
	public List<Hospede> buscarPorSobreNome(String sobreNome){
		return this.hospedeDao.buscarPorSobreNome(sobreNome);
	}

	public void alterar(Hospede hospede) {
		this.hospedeDao.alterar(hospede);
		
	}
}
