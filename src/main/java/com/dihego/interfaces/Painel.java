package com.dihego.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.dihego.Main;

import lombok.Getter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;

public class Painel {

	@Getter
	public static Painel instance = new Painel();
	
	private JFrame frame;
	private JTable table;

	public static void call() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Painel window = new Painel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void destroy() { 
		frame.dispose();
	}

	/**
	 * Create the application.
	 */
	public Painel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 563, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel jPainelTitulo = new JPanel();
		jPainelTitulo.setBounds(10, 11, 527, 37);
		jPainelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelTitulo.setBorder(Main.getBorder());
		frame.getContentPane().add(jPainelTitulo);
		jPainelTitulo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("PAINEL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		jPainelTitulo.add(lblNewLabel);
		jPainelTitulo.setBorder(Main.getBorder());
		
		JPanel jPainelPainel = new JPanel();
		jPainelPainel.setBounds(10, 51, 527, 199);
		jPainelPainel.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelPainel.setBorder(Main.getBorder());
		frame.getContentPane().add(jPainelPainel);
		jPainelPainel.setLayout(null);
		
		JButton btUsuarios = new JButton("Usuários");
		btUsuarios.setBounds(395, 37, 122, 23);
		jPainelPainel.add(btUsuarios);
		
		JButton btItens = new JButton("Items");
		btItens.setBounds(395, 11, 122, 23);
		jPainelPainel.add(btItens);
		
		JLabel lblUsuarioLogado = new JLabel("Olá, %n, oque deseja fazer?");
		lblUsuarioLogado.setBounds(10, 11, 286, 14);
		jPainelPainel.add(lblUsuarioLogado);
		
		JLabel lblCargo = new JLabel("Cargo: %n");
		lblCargo.setBounds(10, 29, 143, 14);
		jPainelPainel.add(lblCargo);
		
		table = new JTable();
		table.setBounds(10, 71, 507, 120);
		jPainelPainel.add(table);
		
		if(Main.getUsuario() != null) { 
			lblUsuarioLogado.setText(lblUsuarioLogado.getText().replace("%n", Main.getUsuario().getNomeLogado()));
			lblCargo.setText(lblCargo.getText().replace("%n", Main.getUsuario().getCargo().getNomePatente()));
		}
	}
}
