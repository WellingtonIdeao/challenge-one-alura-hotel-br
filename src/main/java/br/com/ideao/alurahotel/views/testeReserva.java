package br.com.ideao.alurahotel.views;

import java.time.LocalDate;

import br.com.ideao.alurahotel.model.FormaPagamento;
import br.com.ideao.alurahotel.model.Reserva;

public class testeReserva {

	public static void main(String[] args) {
		FormaPagamento pagamento = new FormaPagamento("Cartão de Credito");
		LocalDate startDate = LocalDate.of(2023, 8, 10);
		LocalDate endDate = LocalDate.of(2023, 8, 20);
		
		Reserva reserva = new Reserva(startDate, endDate);
		reserva.setFormatoPagmentoId(1l);
		System.out.println(startDate);
		System.out.println(reserva.getValor());

	}

}
