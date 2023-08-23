package br.com.ideao.alurahotel.controller;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

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
	
	public Date convertToLocalDateToDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);    
	}
	public LocalDate convertDateToLocalDate(java.util.Date date) {
		return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	

}
