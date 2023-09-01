package br.com.ideao.alurahotel.validators;

import java.time.LocalDate;
import java.util.Date;

import br.com.ideao.alurahotel.utils.Conversor;

public class DateValidator {
	
	 public Boolean datasPreenchidas(Date startDate, Date endDate){
		 return  startDate != null && endDate != null;
	 }
	 
	 public Boolean dataComecaDeHoje(LocalDate startDate, LocalDate endDate) {
		 
		 LocalDate now = LocalDate.now();
		 return startDate.compareTo(now) >= 0 && endDate.compareTo(now) >= 0 ;
	 }
	 
	 public Boolean dataEntradaMenorDataSaida(LocalDate startDate, LocalDate endDate) {
		 return startDate.compareTo(endDate) <= 0;
	 }
	 
	 public Boolean validarDatas(Date startDate, Date endDate){
		 
		 if(!datasPreenchidas(startDate, endDate)) {
			 return false;
		 }
		 LocalDate LocalStartDate = Conversor.convertDateToLocalDate(startDate);
		 LocalDate LocalEndDate = Conversor.convertDateToLocalDate(endDate);

		 return dataComecaDeHoje(LocalStartDate, LocalEndDate) && dataEntradaMenorDataSaida(LocalStartDate, LocalEndDate); 
	 }
}
