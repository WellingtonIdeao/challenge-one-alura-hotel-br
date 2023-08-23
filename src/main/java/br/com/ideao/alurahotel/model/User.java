package br.com.ideao.alurahotel.model;

public class User {
	private Long id;
	private String username;
	private String password;
	
	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
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
	
	@Override
	public String toString() {
		return this.username;
	}
}
