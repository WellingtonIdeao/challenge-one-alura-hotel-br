package br.com.ideao.alurahotel.model;

public class FormaPagamento {
	private Long id;
	private String nome;
	
	public FormaPagamento(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public FormaPagamento(String nome) {
		this.nome = nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
}
