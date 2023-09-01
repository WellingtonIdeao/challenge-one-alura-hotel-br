package br.com.ideao.alurahotel.views;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.ideao.alurahotel.model.FormaPagamento;
import br.com.ideao.alurahotel.model.Reserva;

@SuppressWarnings("serial")
public class ReservaTableModel extends AbstractTableModel {
	private String[] columnNames = {"Numero de Reserva", "Data Check In", "Data Check Out", "Valor", "Forma de Pagamento"};
	private List<Reserva> reservas;
	
	private final int COLUNA_NUMERO_RESERVA = 0;
	private final int COLUNA_DATA_CHECK_IN = 1;
	private final int COLUNA_DATA_CHECK_OUT = 2;
	private final int COLUNA_VALOR = 3;
	private final int COLUNA_FORMA_PAGAMENTO = 4;
	
	public ReservaTableModel() {
		this.reservas = new ArrayList<>();
	}
	public ReservaTableModel(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	@Override
	public int getRowCount() {
		return this.reservas.size();
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reserva	reserva = this.reservas.get(rowIndex);
			switch(columnIndex) {
				case COLUNA_NUMERO_RESERVA: return reserva.getId();
				case COLUNA_DATA_CHECK_IN: return reserva.getDataEntrada();
				case COLUNA_DATA_CHECK_OUT: return reserva.getDataSaida();
				case COLUNA_VALOR: return reserva.getValor();
				case COLUNA_FORMA_PAGAMENTO: return reserva.getFormaPagmento();
			}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0 && columnIndex != 3;
	}
	
	public void addRow(Reserva reserva) {
		this.reservas.add(reserva);
		this.fireTableDataChanged();
	}
	
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Reserva reserva = this.reservas.get(rowIndex);
		
		switch(columnIndex) {
			case COLUNA_NUMERO_RESERVA: 
				reserva.setId((Long) aValue);
				break;
			case COLUNA_DATA_CHECK_IN: 
				reserva.setDataEntrada((LocalDate) aValue);
				break;
			case COLUNA_DATA_CHECK_OUT: 
				reserva.setDataSaida((LocalDate) aValue);
				break;
			case COLUNA_VALOR: 
				reserva.setValor((BigDecimal) aValue);
				break;	
			case COLUNA_FORMA_PAGAMENTO: 
				reserva.setFormatoPagmentoId((FormaPagamento) aValue);
				break;
		}
		fireTableDataChanged();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
	        case COLUNA_NUMERO_RESERVA:
	        	return Long.class;
	        case COLUNA_DATA_CHECK_IN:
	        	return LocalDate.class;
	        case COLUNA_DATA_CHECK_OUT:
	            return LocalDate.class;
	        case COLUNA_VALOR:
	            return BigDecimal.class;
	        case COLUNA_FORMA_PAGAMENTO:
	            return FormaPagamento.class;            
		}
		return null;
	}
	
	public void clear() {
		this.reservas.clear();
		this.fireTableDataChanged();
	}
	
	public void removeRow(int row){
		this.reservas.remove(row);
        fireTableRowsDeleted(row, row);
	}
}
