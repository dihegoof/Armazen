package com.dihego.interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.dihego.Cargo;
import com.dihego.Main;
import com.dihego.construtor.UsuarioDAO;

import lombok.Getter;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login {
	
	@Getter
	public static Login instance = new Login();
	@Getter
	private JFrame Autenticacao;
	private JTextField txtRaMilitar;
	private JPasswordField txtSenha;
	
	public static void call() { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.Autenticacao.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void destroy() {
		Autenticacao.dispose();
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Autenticacao = new JFrame();
		Autenticacao.setTitle("Autenticação");
		Autenticacao.setBounds(100, 100, 354, 188);
		Autenticacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Autenticacao.setLocationRelativeTo(null);
		Autenticacao.getContentPane().setLayout(null);
		
		JPanel jPainelTitulo = new JPanel();
		jPainelTitulo.setBounds(10, 11, 318, 29);
		jPainelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelTitulo.setBorder(Main.getBorder());
		Autenticacao.getContentPane().add(jPainelTitulo);
		jPainelTitulo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAutenticao = new JLabel("AUTENTICAÇÃO");
		lblAutenticao.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutenticao.setFont(new Font("Tahoma", Font.BOLD, 15));
		jPainelTitulo.add(lblAutenticao, BorderLayout.CENTER);
		
		JPanel jPainelCredenciais = new JPanel();
		jPainelCredenciais.setBounds(10, 43, 216, 95);
		jPainelCredenciais.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelCredenciais.setBorder(Main.getBorder());
		Autenticacao.getContentPane().add(jPainelCredenciais);
		jPainelCredenciais.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RA Militar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 27, 87, 14);
		jPainelCredenciais.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 52, 87, 14);
		jPainelCredenciais.add(lblNewLabel_1);
		
		txtRaMilitar = new JTextField();
		txtRaMilitar.setBounds(95, 24, 105, 20);
		jPainelCredenciais.add(txtRaMilitar);
		txtRaMilitar.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(95, 49, 105, 20);
		jPainelCredenciais.add(txtSenha);
		
		JPanel jPainelBotoes = new JPanel();
		jPainelBotoes.setLayout(null);
		jPainelBotoes.setBounds(236, 43, 92, 95);
		jPainelBotoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelBotoes.setBorder(Main.getBorder());
		Autenticacao.getContentPane().add(jPainelBotoes);
		
		Button btAutenticar = new Button("Autenticar");
		btAutenticar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtRaMilitar.getText().equals("admin") && new String(txtSenha.getPassword()).equals("admin")) { 
					UsuarioDAO usuario = new UsuarioDAO("123456789123", "Dihego", "Nunes", "dihegofn.c@gmail.com", "admin", 'm', Cargo.CORONEL, true, new ArrayList<String>());
					Main.setUsuario(usuario);		
					destroy();
					Painel.call();
				} else { 
					clear();
					JOptionPane.showMessageDialog(null, "Credenciais inválidos!");
				}
			}
		});
		btAutenticar.setFont(new Font("Dialog", Font.BOLD, 12));
		btAutenticar.setBounds(10, 10, 70, 22);
		jPainelBotoes.add(btAutenticar);
		
		Button btRecuperar = new Button("Recuperar");
		btRecuperar.setFont(new Font("Dialog", Font.BOLD, 12));
		btRecuperar.setBounds(10, 38, 70, 22);
		jPainelBotoes.add(btRecuperar);
		
		Button btCadastrar = new Button("Cadastrar");
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destroy();
				Cadastro.call();
			}
		});
		btCadastrar.setFont(new Font("Dialog", Font.BOLD, 12));
		btCadastrar.setBounds(10, 65, 70, 22);
		jPainelBotoes.add(btCadastrar);
	}
	
	public void clear() { 
		txtRaMilitar.setText(null);
		txtSenha.setText(null);
	}
}
