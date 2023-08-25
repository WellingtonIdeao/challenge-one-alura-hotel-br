package br.com.ideao.alurahotel.model;

import java.time.LocalDate;

public class Hospede {
	private Long id;
	private String nome;
	private String sobreNome;
	private LocalDate dataNascimento;
	private Nacionalidade nacionalidade;
	private String telefone;
	private Reserva reserva;
	
	public Hospede(Long id, String nome, String sobreNome, LocalDate dataNascimento, Nacionalidade nacionalidade, String telefone, Reserva reserva) {
		this(nome, sobreNome, dataNascimento, nacionalidade, telefone, reserva);
		this.id = id;
	}
	
	public Hospede(String nome, String sobreNome, LocalDate dataNascimento, Nacionalidade nacionalidade, String telefone, Reserva reserva) {
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.reserva = reserva;
		this.nacionalidade = nacionalidade;
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
	
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	
	public String getSobreNome() {
		return sobreNome;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}
	
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public Nacionalidade getNacionalidade() {
		return this.nacionalidade;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public Reserva getReserva() {
		return this.reserva;
	}
}
