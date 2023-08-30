package br.com.ideao.alurahotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
	private Long id;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private BigDecimal valor = BigDecimal.ZERO;
	private FormaPagamento formaPagamento;
	private BigDecimal taxa = BigDecimal.ZERO;
	
	public Reserva() {
		super();
	}
	
	public Reserva(Long id, LocalDate dataEntrada, LocalDate dataSaida, FormaPagamento formaPagamento) {
		this(dataEntrada, dataSaida, formaPagamento);
		this.id = id;
	}
	public Reserva(LocalDate dataEntrada, LocalDate dataSaida, FormaPagamento formaPagamento) {
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.formaPagamento = formaPagamento;
		this.taxa = new BigDecimal("200");
		this.valor = calcularValor();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public BigDecimal getValor() {
		return this.valor;
	}
	
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	public LocalDate getDataEntrada() {
		return this.dataEntrada;
	}
	
	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	public LocalDate getDataSaida() {
		return this.dataSaida;
	}
	
	public void setFormatoPagmentoId(FormaPagamento formatoPagmento) {
		this.formaPagamento = formatoPagmento;
	}
	
	public FormaPagamento getFormaPagmento() {
		return this.formaPagamento;
	}
	
	private BigDecimal calcularValor() {
		return this.taxa.multiply(new BigDecimal(calcularDiasReservados()));
	}
	private Long calcularDiasReservados() {
		return ChronoUnit.DAYS.between(this.dataEntrada, this.dataSaida.plusDays(1));
	}

	public void setValor(BigDecimal aValue) {
		this.valor = aValue;
		
	}

}
