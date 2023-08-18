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
import java.awt.FlowLayout;

public class Login {
	
	@Getter
	public static Login instance = new Login();
	@Getter
	private JFrame autenticacao;
	private JTextField txtRaMilitar;
	private JPasswordField txtSenha;
	
	public static void call() { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.autenticacao.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void destroy() {
		autenticacao.dispose();
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
		autenticacao = new JFrame();
		autenticacao.setTitle("Autenticação");
		autenticacao.setBounds(100, 100, 279, 222);
		autenticacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		autenticacao.setLocationRelativeTo(null);
		autenticacao.getContentPane().setLayout(null);
		
		JPanel jPainelTitulo = new JPanel();
		jPainelTitulo.setBounds(10, 11, 242, 29);
		jPainelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelTitulo.setBorder(Main.getBorder());
		autenticacao.getContentPane().add(jPainelTitulo);
		jPainelTitulo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAutenticao = new JLabel("AUTENTICAÇÃO");
		lblAutenticao.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutenticao.setFont(new Font("Tahoma", Font.BOLD, 15));
		jPainelTitulo.add(lblAutenticao, BorderLayout.CENTER);
		
		JPanel jPainelCredenciais = new JPanel();
		jPainelCredenciais.setBounds(10, 43, 242, 95);
		jPainelCredenciais.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelCredenciais.setBorder(Main.getBorder());
		autenticacao.getContentPane().add(jPainelCredenciais);
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
		txtRaMilitar.setBounds(95, 24, 126, 20);
		jPainelCredenciais.add(txtRaMilitar);
		txtRaMilitar.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(95, 49, 126, 20);
		jPainelCredenciais.add(txtSenha);
		
		JPanel jPainelBotoes = new JPanel();
		jPainelBotoes.setBounds(10, 141, 242, 35);
		jPainelBotoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelBotoes.setBorder(Main.getBorder());
		autenticacao.getContentPane().add(jPainelBotoes);
		jPainelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Button btAutenticar = new Button("Autenticar");
		jPainelBotoes.add(btAutenticar);
		btAutenticar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtRaMilitar.getText().equals("admin") && new String(txtSenha.getPassword()).equals("admin")) { 
					UsuarioDAO usuario = new UsuarioDAO("123456789123", "Dihego", "Nunes", "dihegofn.c@gmail.com", "admin", System.currentTimeMillis(), 'm', Cargo.CORONEL, true, new ArrayList<String>());
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
		
		Button btRecuperar = new Button("Recuperar");
		jPainelBotoes.add(btRecuperar);
		btRecuperar.setFont(new Font("Dialog", Font.BOLD, 12));
		
		Button btCadastrar = new Button("Cadastrar");
		jPainelBotoes.add(btCadastrar);
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.isAutenticado()) { 
					destroy();
					Cadastro.call();
				}
			}
		});
		btCadastrar.setFont(new Font("Dialog", Font.BOLD, 12));
	}
	
	public void clear() { 
		txtRaMilitar.setText(null);
		txtSenha.setText(null);
	}
}
