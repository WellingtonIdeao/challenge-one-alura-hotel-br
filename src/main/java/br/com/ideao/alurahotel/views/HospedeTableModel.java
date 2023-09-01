package br.com.ideao.alurahotel.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.ideao.alurahotel.model.Hospede;
import br.com.ideao.alurahotel.model.Nacionalidade;
import br.com.ideao.alurahotel.model.Reserva;

@SuppressWarnings("serial")
public class HospedeTableModel extends AbstractTableModel {
	
	private String[] columnNames = {
			"Número de Hóspede", "Nome", "Sobrenome", "Data de Nascimento",
			"Nacionalidade", "Telefone", "Número de Reserva"
	};
	private List<Hospede> hospedes;
	
	private final int COLUNA_NUMERO_HOSPEDE = 0;
	private final int COLUNA_NOME= 1;
	private final int COLUNA_SOBRENOME = 2;
	private final int COLUNA_DATA_NASC = 3;
	private final int COLUNA_NACIONALIDADE = 4;
	private final int COLUNA_TELEFONE = 5;
	private final int COLUNA_NUMERO_RESERVA = 6;
	
	
	public HospedeTableModel() {
		this.hospedes = new ArrayList<>();
	}
	
	public HospedeTableModel(List<Hospede> hospedes) {
		this.hospedes = hospedes;
	}
	
	@Override
	public int getRowCount() {
		return this.hospedes.size();
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
		Hospede hospede = this.hospedes.get(rowIndex);
		switch(columnIndex) {
			case COLUNA_NUMERO_HOSPEDE: return hospede.getId();
			case COLUNA_NOME: return hospede.getNome();
			case COLUNA_SOBRENOME: return hospede.getSobreNome();
			case COLUNA_DATA_NASC: return hospede.getDataNascimento();
			case COLUNA_NACIONALIDADE: return hospede.getNacionalidade();
			case COLUNA_TELEFONE: return hospede.getTelefone();
			case COLUNA_NUMERO_RESERVA: return hospede.getReserva();
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0 && columnIndex < 6;
	}
	
	public void addRow(Hospede hospede) {
		this.hospedes.add(hospede);
		this.fireTableDataChanged();
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Hospede hospede = this.hospedes.get(rowIndex);
		switch(columnIndex) {
		case COLUNA_NUMERO_HOSPEDE:
			hospede.setId((Long) aValue);
			break;
		case COLUNA_NOME: 
			hospede.setNome((String) aValue);
			break;
		case COLUNA_SOBRENOME: 
			hospede.setSobreNome((String) aValue);
			break;
		case COLUNA_DATA_NASC: 
			hospede.setDataNascimento((LocalDate) aValue);
			break;
		case COLUNA_NACIONALIDADE:
			hospede.setNacionalidade((Nacionalidade) aValue);
			break;
		case COLUNA_TELEFONE:
			hospede.setTelefone((String) aValue);
			break;	
		case COLUNA_NUMERO_RESERVA:
			hospede.setReserva((Reserva) aValue );
			break;
		}
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case COLUNA_NUMERO_HOSPEDE:
				return Long.class;
			case COLUNA_NOME: 
				return String.class;
			case COLUNA_SOBRENOME: 
				return String.class;
			case COLUNA_DATA_NASC: 
				return LocalDate.class;
			case COLUNA_NACIONALIDADE: 
				return Nacionalidade.class;
			case COLUNA_TELEFONE: 
				return String.class;
			case COLUNA_NUMERO_RESERVA: 
				return Reserva.class;
		}
		return null;
	}
	
	public void clear() {
		this.hospedes.clear();
		this.fireTableDataChanged();
	}

	public void removeRow(int row) {
		this.hospedes.remove(row);
        fireTableRowsDeleted(row, row);		
	}
}
