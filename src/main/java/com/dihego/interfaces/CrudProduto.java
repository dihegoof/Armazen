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

import com.dihego.Main;

public class CrudProduto {

	@Getter
	public static CrudProduto instance = new CrudProduto();
	
	private JFrame frame;

	public static void call(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrudProduto window = new CrudProduto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrudProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel jPainelTitulo = new JPanel();
		jPainelTitulo.setBounds(10, 11, 414, 38);
		jPainelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelTitulo.setBorder(Main.getBorder());
		frame.getContentPane().add(jPainelTitulo);
		jPainelTitulo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("PAINEL DE USUÁRIOS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		jPainelTitulo.add(lblNewLabel);
		
		JPanel jPainelCrud = new JPanel();
		jPainelCrud.setBounds(10, 51, 414, 157);
		jPainelCrud.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelCrud.setBorder(Main.getBorder());
		frame.getContentPane().add(jPainelCrud);
		
		JPanel jPainelBotoes = new JPanel();
		jPainelBotoes.setBounds(10, 211, 414, 38);
		jPainelBotoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelBotoes.setBorder(Main.getBorder());
		frame.getContentPane().add(jPainelBotoes);
	}

}
