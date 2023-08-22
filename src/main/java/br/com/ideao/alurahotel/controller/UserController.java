package br.com.ideao.alurahotel.controller;

import java.sql.Connection;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.ideao.alurahotel.dao.UserDao;
import br.com.ideao.alurahotel.model.User;
import br.com.ideao.alurahotel.utils.ConnectionFactory;

public class UserController {
	private UserDao userDao;
	
	public UserController() {
		Connection connection = new ConnectionFactory().getConnection();
		this.userDao = new UserDao(connection);
	}
	
	private User buscar(String username) {
		return this.userDao.buscar(username);
	}
	
	public Boolean autenticate(String username, String password) {
		String passwordSha256 = DigestUtils.sha256Hex(password);
		User user = this.buscar(username);
		return (user != null && user.getPassword().equals(passwordSha256));
	}
	
}
