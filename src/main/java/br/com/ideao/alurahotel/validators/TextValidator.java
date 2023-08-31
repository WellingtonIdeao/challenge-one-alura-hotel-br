package br.com.ideao.alurahotel.validators;

public class TextValidator {
	
	public Boolean isDigit(String text) {
		Long digit;
		try {
			digit = Long.parseLong(text);
		} catch (Exception e) {
			return false;
		}
		return digit instanceof Long;
	}
	
	public Boolean isEmpty(String text) {
		return text.isBlank() || text.isEmpty(); 
	}
	
}
