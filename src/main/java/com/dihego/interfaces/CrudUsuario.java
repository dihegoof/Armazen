package com.dihego.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;

import lombok.Getter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.dihego.Cargo;
import com.dihego.Main;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrudUsuario {

	@Getter
	public static CrudUsuario instance = new CrudUsuario();
	
	private JFrame painelUsuario;
	private JTextField txtRaMilitar;
	private final JComboBox<String> cbCargo = new JComboBox<String>();
	private JTextField txtPermissão;

	public static void call() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudUsuario window = new CrudUsuario();
					window.painelUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void destroy() { 
		painelUsuario.dispose();
	}
	
	/**
	 * Create the application.
	 */
	public CrudUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		painelUsuario = new JFrame();
		painelUsuario.setTitle("Painel de Usuários");
		painelUsuario.setBounds(100, 100, 383, 255);
		painelUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painelUsuario.setLocationRelativeTo(null);
		painelUsuario.getContentPane().setLayout(null);
		
		JPanel jPainelTitulo = new JPanel();
		jPainelTitulo.setBounds(10, 11, 347, 38);
		jPainelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelTitulo.setBorder(Main.getBorder());
		painelUsuario.getContentPane().add(jPainelTitulo);
		jPainelTitulo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("PAINEL DE USUÁRIOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		jPainelTitulo.add(lblNewLabel);
		
		JPanel jPainelCrud = new JPanel();
		jPainelCrud.setBounds(10, 51, 347, 118);
		jPainelCrud.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelCrud.setBorder(Main.getBorder());
		painelUsuario.getContentPane().add(jPainelCrud);
		jPainelCrud.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("RA Militar:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 59, 72, 20);
		jPainelCrud.add(lblNewLabel_1);
		
		txtRaMilitar = new JTextField();
		txtRaMilitar.setBounds(75, 59, 115, 20);
		jPainelCrud.add(txtRaMilitar);
		txtRaMilitar.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cargo:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(200, 59, 50, 20);
		jPainelCrud.add(lblNewLabel_1_1);
		cbCargo.setBounds(244, 59, 93, 20);
		jPainelCrud.add(cbCargo);
		
		JLabel lblNome = new JLabel("Nome: %n");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(10, 10, 327, 20);
		lblNome.setText(lblNome.getText().replace("%n", "???"));
		jPainelCrud.add(lblNome);
		
		JLabel lblUltimoAcesso = new JLabel("Último acesso há %t");
		lblUltimoAcesso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUltimoAcesso.setBounds(10, 33, 327, 20);
		lblUltimoAcesso.setText(lblUltimoAcesso.getText().replace("%t", "???"));
		jPainelCrud.add(lblUltimoAcesso);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nova permissão:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(10, 85, 105, 20);
		jPainelCrud.add(lblNewLabel_1_2);
		
		txtPermissão = new JTextField();
		txtPermissão.setColumns(10);
		txtPermissão.setBounds(115, 85, 222, 20);
		jPainelCrud.add(txtPermissão);
		
		JPanel jPainelBotoes = new JPanel();
		jPainelBotoes.setBounds(10, 172, 347, 38);
		jPainelBotoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelBotoes.setBorder(Main.getBorder());
		painelUsuario.getContentPane().add(jPainelBotoes);
		jPainelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.isAutenticado()) { 
					destroy();
					Painel.call();
				}
			}
		});
		btVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPainelBotoes.add(btVoltar);
		
		JButton btBuscar = new JButton("Buscar");
		btBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPainelBotoes.add(btBuscar);
		
		JButton btExcluir = new JButton("Excluir");
		btExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPainelBotoes.add(btExcluir);
		
		JButton btSalvar = new JButton("Salvar");
		btSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPainelBotoes.add(btSalvar);
		
		for(Cargo cargos : Cargo.values()) { 
			cbCargo.addItem(cargos.getNomePatente());
		}
	}
}
