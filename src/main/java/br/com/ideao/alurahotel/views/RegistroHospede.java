package br.com.ideao.alurahotel.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;

import com.toedter.calendar.JDateChooser;

import br.com.ideao.alurahotel.controller.HospedeController;
import br.com.ideao.alurahotel.controller.NacionalidadeController;
import br.com.ideao.alurahotel.model.Hospede;
import br.com.ideao.alurahotel.model.Nacionalidade;
import br.com.ideao.alurahotel.model.Reserva;
import br.com.ideao.alurahotel.utils.Conversor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroHospede extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtTelefone;
	private JTextField txtNreserva;
	private JDateChooser txtDataN;
	private JComboBox<Nacionalidade> txtNacionalidade;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	private NacionalidadeController nacionalidadeController;
	private HospedeController hospedeController;
	private Hospede hospede;
	private Reserva reserva;

	/**
	 * Create the frame.
	 */
	public RegistroHospede(Reserva reserva) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHospede.class.getResource("/br/com/ideao/alurahotel/imagens/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		
		this.nacionalidadeController = new NacionalidadeController();
		this.hospedeController = new HospedeController();
		this.reserva = reserva;
		
		JPanel header = new JPanel();
		header.setBounds(-54, 0, 910, 36);
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
		
		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		contentPane.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color(12, 138, 199));
			     labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNome.setBounds(560, 135, 285, 33);
		txtNome.setBackground(Color.WHITE);
		txtNome.setColumns(10);
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtSobrenome.setBounds(560, 204, 285, 33);
		txtSobrenome.setColumns(10);
		txtSobrenome.setBackground(Color.WHITE);
		txtSobrenome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtSobrenome);
		
		txtDataN = new JDateChooser();
		txtDataN.setBounds(560, 278, 285, 36);
		txtDataN.getCalendarButton().setIcon(new ImageIcon(RegistroHospede.class.getResource("/br/com/ideao/alurahotel/imagens/icon-reservas.png")));
		txtDataN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtDataN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtDataN);
		
		txtNacionalidade = new JComboBox<>();
		txtNacionalidade.setBounds(560, 350, 289, 36);
		txtNacionalidade.setBackground(SystemColor.text);
		txtNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
		List<Nacionalidade> nacionalidades = this.carregarNacionalidades();
		for(Nacionalidade nc:  nacionalidades) {
			txtNacionalidade.addItem(nc);
		}
		contentPane.add(txtNacionalidade);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(562, 119, 253, 14);
		lblNome.setForeground(SystemColor.textInactiveText);
		lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNome);
		
		JLabel lblSobrenome = new JLabel("SOBRENOME");
		lblSobrenome.setBounds(560, 189, 255, 14);
		lblSobrenome.setForeground(SystemColor.textInactiveText);
		lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblSobrenome);
		
		JLabel lblDataN = new JLabel("DATA DE NASCIMENTO");
		lblDataN.setBounds(560, 256, 255, 14);
		lblDataN.setForeground(SystemColor.textInactiveText);
		lblDataN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblDataN);
		
		JLabel lblNacionalidade = new JLabel("NACIONALIDADE");
		lblNacionalidade.setBounds(560, 326, 255, 14);
		lblNacionalidade.setForeground(SystemColor.textInactiveText);
		lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidade);
		
		JLabel lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setBounds(562, 406, 253, 14);
		lblTelefone.setForeground(SystemColor.textInactiveText);
		lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefone.setBounds(560, 424, 285, 33);
		txtTelefone.setColumns(10);
		txtTelefone.setBackground(Color.WHITE);
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefone);
		
		JLabel lblTitulo = new JLabel("REGISTRO HÓSPEDE");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);
		
		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);
		
		txtNreserva = new JTextField();
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 495, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNreserva.setText(String.valueOf(reserva.getId()));
		contentPane.add(txtNreserva);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);
		
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);
		
		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);
		
		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);
		
		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);
		
		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);
		
		JPanel btnsalvar = new JPanel();
		btnsalvar.setBounds(723, 560, 122, 35);
		btnsalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(camposValidos()) {
					atualizaDadosHospede();
					registrarHospede();
					Sucesso janelaSucesso = new Sucesso();
					janelaSucesso.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
				}
			}
		});
		btnsalvar.setLayout(null);
		btnsalvar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnsalvar);
		btnsalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel labelSalvar = new JLabel("SALVAR");
		labelSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		labelSalvar.setForeground(Color.WHITE);
		labelSalvar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelSalvar.setBounds(0, 0, 122, 35);
		btnsalvar.add(labelSalvar);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imageFundo = new JLabel("");
		imageFundo.setBounds(0, 121, 479, 502);
		panel.add(imageFundo);
		imageFundo.setIcon(new ImageIcon(RegistroHospede.class.getResource("/br/com/ideao/alurahotel/imagens/registro.png")));
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHospede.class.getResource("/br/com/ideao/alurahotel/imagens/Ha-100px.png")));
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" y "y"
		private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
	    private List<Nacionalidade> carregarNacionalidades(){
	    	return this.nacionalidadeController.listar();
	    }
	    
	    private Boolean camposValidos() {
	    	return this.txtNome.getText()!= null && this.txtSobrenome.getText()
	    			!= null && validarDataNasc() && validarTelefone(txtTelefone.getText());
	    }
	    
	    private Hospede registrarHospede() {
	    	return this.hospedeController.cadastrar(this.hospede);
	    }
	    
	    private void atualizaDadosHospede() {
	    	LocalDate date = Conversor.convertDateToLocalDate(this.txtDataN.getDate()); 
	    	Nacionalidade nacionalidade = (Nacionalidade) this.txtNacionalidade.getSelectedItem();
	    	this.hospede = new Hospede(this.txtNome.getText(), this.txtSobrenome.getText(), date, nacionalidade,this.txtTelefone.getText(), this.reserva);
	    }
	    
	    private Boolean validarTelefone(String telefone) {
	    	String regex = "^9?\\d{8}$";
	    	Pattern pattern = Pattern.compile(regex);
	    	Matcher  matcher = pattern.matcher(telefone);
	    	return matcher.matches();
	    }
	    
	    private Boolean validarDataNasc() {
	    	
	    	if(txtDataN == null) {
	    		return false;
	    	}
	    	LocalDate today = LocalDate.now();
	    	LocalDate date = Conversor.convertDateToLocalDate(txtDataN.getDate());
	    	return  date.compareTo(today) < 0;
	    }
}
