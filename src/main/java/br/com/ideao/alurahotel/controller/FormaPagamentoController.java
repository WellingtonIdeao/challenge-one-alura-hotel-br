package br.com.ideao.alurahotel.controller;

import java.sql.Connection;
import java.util.List;

import br.com.ideao.alurahotel.dao.FormaPagamentoDao;
import br.com.ideao.alurahotel.model.FormaPagamento;
import br.com.ideao.alurahotel.utils.JdbcUtil;

public class FormaPagamentoController {
	private FormaPagamentoDao formaPagamentoDao;
	
	public FormaPagamentoController() {
		Connection connection = JdbcUtil.getConnection();
		this.formaPagamentoDao = new FormaPagamentoDao(connection);		
	}
	public List<FormaPagamento> listar(){
		return this.formaPagamentoDao.listar();
	}
	

}
