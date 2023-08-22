package br.com.ideao.alurahotel.model;

public class User {
	private String username;
	private String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	public String getUsername() {
		return this.username;
	}
}
