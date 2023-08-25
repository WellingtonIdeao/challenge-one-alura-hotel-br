package br.com.ideao.alurahotel.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Conversor {

	public static Date convertToLocalDateToDate(LocalDate dateToConvert) {
	    return java.sql.Date.valueOf(dateToConvert);    
	}
	
	public static LocalDate convertDateToLocalDate(java.util.Date date) {
		return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
}
