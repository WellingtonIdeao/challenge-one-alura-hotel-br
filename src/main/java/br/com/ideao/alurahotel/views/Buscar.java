package br.com.ideao.alurahotel.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.ideao.alurahotel.controller.FormaPagamentoController;
import br.com.ideao.alurahotel.controller.HospedeController;
import br.com.ideao.alurahotel.controller.ReservaController;
import br.com.ideao.alurahotel.model.FormaPagamento;
import br.com.ideao.alurahotel.model.Hospede;
import br.com.ideao.alurahotel.model.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.github.lgooddatepicker.tableeditors.DateTableEditor;


@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private ReservaTableModel modeloReserva;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ReservaController reservaController;
	private HospedeController hospedeController;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/br/com/ideao/alurahotel/imagens/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		this.reservaController = new ReservaController();
		this.hospedeController = new HospedeController();
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		
		JComboBox<FormaPagamento> comboBoxFormaPagamento = new JComboBox<>();
		FormaPagamentoController fpc = new FormaPagamentoController();
		
		List<FormaPagamento> formasPagamentos = fpc.listar();
		for(FormaPagamento fp:  formasPagamentos) {
			comboBoxFormaPagamento.addItem(fp);
		}
		
		tbReservas = new JTable(new ReservaTableModel());
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));

		tbReservas.setDefaultEditor(LocalDate.class, new DateTableEditor());
		tbReservas.setDefaultRenderer(LocalDate.class,new DateTableEditor());
		tbReservas.setDefaultEditor(FormaPagamento.class, new DefaultCellEditor(comboBoxFormaPagamento));
		modeloReserva = (ReservaTableModel) tbReservas.getModel();

		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/br/com/ideao/alurahotel/imagens/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/br/com/ideao/alurahotel/imagens/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/br/com/ideao/alurahotel/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario menuUsuario = new MenuUsuario();
				menuUsuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario menuUsuario = new MenuUsuario();
				menuUsuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelectedIndex() == 0) {
					try {
						Long id = Long.parseLong(txtBuscar.getText());
						Reserva reserva = buscarReserva(id);
						if(reserva == null) {
							JOptionPane.showMessageDialog(null, "Reserva não encontrada");
							limparTabela(modeloReserva);
						}else
							preencherTabela(reserva);
					} catch(Exception ex) {
						limparTabela(modeloReserva);
					}
				}
//				if(panel.getSelectedIndex() == 1) {
//					limparTabela(modeloHospedes);
//					List<Hospede> hospedes = hospedeController.buscarPorSobreNome(txtBuscar.getText());
//					preencherTabela(hospedes);
//				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel.getSelectedIndex() == 0) {
						Reserva reserva = alterar();
						if (reserva!=null) {
							limparTabela(modeloReserva);
							reserva = buscarReserva(reserva.getId());
							preencherTabela(reserva);
						}
				}		
			}
		});
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	private Reserva alterar() {
		Reserva reserva = null;
		try {
			Object objetoLinha = (Object) modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
			if (objetoLinha instanceof Long) {
				Long id = (Long) objetoLinha;
				LocalDate data_entrada = (LocalDate)modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1);
				LocalDate data_saida = (LocalDate)modeloReserva.getValueAt(tbReservas.getSelectedRow(), 2);
				BigDecimal valor = (BigDecimal)modeloReserva.getValueAt(tbReservas.getSelectedRow(), 3);
				FormaPagamento fp = (FormaPagamento) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4);
				System.out.println("id = "+ id +", data_entrada = "+data_entrada
						+", data_saida = "+data_saida+", valor = "+valor+", forma_pagamento = "+fp);
				
				reserva  = new Reserva(id, data_entrada, data_saida, fp);
				if(dataComecaDeHoje(data_entrada, data_saida) && dataEntradaMenorDataSaida(data_entrada, data_saida)) {
					this.reservaController.alterar(reserva);
				}else {
					JOptionPane.showMessageDialog(null, "Por favor, coloque datas válidas");
				}	
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, selecionar o ID");
			}
		} catch( RuntimeException e) {
			JOptionPane.showMessageDialog(null, "Por favor, selecionar o ID");
		}
		return reserva;
	}

	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	 private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		 int x = evt.getXOnScreen();
	     int y = evt.getYOnScreen();
	     this.setLocation(x - xMouse, y - yMouse);
	 }
	    
	 private void preencherTabela(Reserva reserva) {
			try {
				modeloReserva.addRow(reserva);	
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	 }
	 
	 private void preencherTabela(List<Hospede> hospedes) {
			try {
				for(Hospede hospede: hospedes) {
					modeloHospedes.addRow(new Object[]{
							hospede.getId(), hospede.getNome(), hospede.getSobreNome(),
							hospede.getDataNascimento(), hospede.getNacionalidade().getNome(),
							hospede.getTelefone(), hospede.getReserva().getId()
					});	
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	 }
		 
	 private void limparTabela(ReservaTableModel tableModel) {
		 tableModel.clear();
	 }
	 private Reserva buscarReserva(Long id) {
		 	Long.parseLong(txtBuscar.getText());
		 	return reservaController.buscarPorId(id);
	 }
	 
	 private Boolean dataComecaDeHoje(LocalDate startDate, LocalDate endDate) {
		 
		 LocalDate now = LocalDate.now();
		 return startDate.compareTo(now) >= 0 && endDate.compareTo(now) >= 0 ;
	 }
	 
	 private Boolean dataEntradaMenorDataSaida(LocalDate startDate, LocalDate endDate) {
		 return startDate.compareTo(endDate) <= 0;
	 }
}
