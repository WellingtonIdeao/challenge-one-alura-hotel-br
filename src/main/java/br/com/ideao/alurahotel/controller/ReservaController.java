package br.com.ideao.alurahotel.controller;

import java.sql.Connection;

import br.com.ideao.alurahotel.dao.ReservaDao;
import br.com.ideao.alurahotel.model.Reserva;
import br.com.ideao.alurahotel.utils.JdbcUtil;

public class ReservaController {
	
	private ReservaDao reservaDao;
	
	public ReservaController() {
		Connection connection = JdbcUtil.getConnection();
		this.reservaDao = new ReservaDao(connection);
	}
	
	public Reserva cadastrar(Reserva reserva) {
		return this.reservaDao.cadastrar(reserva);
	}
	
	public Reserva buscarPorId(Long id){
		return this.reservaDao.buscarPorId(id);
	}
}
