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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Painel {

	@Getter
	public static Painel instance = new Painel();
	
	private JFrame painel;

	public static void call() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Painel window = new Painel();
					window.painel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void destroy() { 
		painel.dispose();
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
		painel = new JFrame();
		painel.setTitle("Painel");
		painel.setBounds(100, 100, 563, 300);
		painel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painel.setLocationRelativeTo(null);
		painel.getContentPane().setLayout(null);
		
		JPanel jPainelTitulo = new JPanel();
		jPainelTitulo.setBounds(10, 11, 527, 37);
		jPainelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelTitulo.setBorder(Main.getBorder());
		painel.getContentPane().add(jPainelTitulo);
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
		painel.getContentPane().add(jPainelPainel);
		jPainelPainel.setLayout(null);
		
		JButton btUsuarios = new JButton("Usuários");
		btUsuarios.setFont(new Font("Tahoma", Font.BOLD, 11));
		btUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.getUsuario().isAutenticado()) { 
					destroy();
					CrudUsuario.call();
				}
			}
		});
		btUsuarios.setBounds(395, 37, 122, 23);
		jPainelPainel.add(btUsuarios);
		
		JButton btItens = new JButton("Items");
		btItens.setFont(new Font("Tahoma", Font.BOLD, 11));
		btItens.setBounds(395, 11, 122, 23);
		jPainelPainel.add(btItens);
		
		JLabel lblUsuarioLogado = new JLabel("Olá, %n, oque deseja fazer?");
		lblUsuarioLogado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsuarioLogado.setBounds(10, 11, 286, 14);
		jPainelPainel.add(lblUsuarioLogado);
		
		JLabel lblCargo = new JLabel("Cargo: %n");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCargo.setBounds(10, 29, 143, 14);
		jPainelPainel.add(lblCargo);
		
		if(Main.isAutenticado()) { 
			lblUsuarioLogado.setText(lblUsuarioLogado.getText().replace("%n", Main.getUsuario().getNomeLogado()));
			lblCargo.setText(lblCargo.getText().replace("%n", Main.getUsuario().getCargo().getNomePatente()));
		}
	}
}
