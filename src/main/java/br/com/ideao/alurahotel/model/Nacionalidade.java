package br.com.ideao.alurahotel.model;

public class Nacionalidade {
	private Long id;
	private String nome;
	
	public Nacionalidade(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public Nacionalidade(String nome) {
		this.nome = nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}
