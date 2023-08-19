package com.dihego.interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.dihego.Cargo;
import com.dihego.Main;
import com.dihego.construtor.UsuarioDAO;

import lombok.Getter;

import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class Cadastro {
	
	@Getter
	public static Cadastro instance = new Cadastro();
	private JFrame cadastro;
	private JTextField txtPrimeiroNome;
	private JTextField txtSegundoNome;
	private JTextField txtRaMilitar;
	private JTextField txtEmail;
	private JPasswordField txtSenha;
	private JRadioButton rdMasculino;
	private JRadioButton rdFeminino;
	
	public static void call() { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro window = new Cadastro();
					window.cadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void destroy() {	
		cadastro.dispose();
	}

	/**
	 * Create the application.
	 */
	public Cadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cadastro = new JFrame();
		cadastro.setTitle("Cadastro");
		cadastro.setBounds(100, 100, 481, 227);
		cadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadastro.setLocationRelativeTo(null);
		cadastro.getContentPane().setLayout(null);
		
		JPanel jPainelTitulo = new JPanel();
		jPainelTitulo.setBounds(10, 11, 448, 33);
		jPainelTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelTitulo.setBorder(Main.getBorder());
		cadastro.getContentPane().add(jPainelTitulo);
		jPainelTitulo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCadastro = new JLabel("CADASTRO");
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 15));
		jPainelTitulo.add(lblCadastro);
		
		JPanel jPainelDados = new JPanel();
		jPainelDados.setBounds(10, 47, 448, 98);
		jPainelDados.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelDados.setBorder(Main.getBorder());
		cadastro.getContentPane().add(jPainelDados);
		jPainelDados.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Primeiro Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 93, 14);
		jPainelDados.add(lblNewLabel);
		
		txtPrimeiroNome = new JTextField();
		txtPrimeiroNome.addKeyListener(new KeyListener() {
			
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }

			@Override
			public void keyPressed(KeyEvent e) { }

			@Override
			public void keyReleased(KeyEvent e) { }
        });
		
		txtPrimeiroNome.setBounds(108, 8, 148, 20);
		jPainelDados.add(txtPrimeiroNome);
		txtPrimeiroNome.setColumns(10);
		
		JLabel lblSegundoNome = new JLabel("Segundo Nome:");
		lblSegundoNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSegundoNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblSegundoNome.setBounds(10, 39, 93, 14);
		jPainelDados.add(lblSegundoNome);
		
		txtSegundoNome = new JTextField();
		txtSegundoNome.addKeyListener(new KeyListener() {
			
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
            
            @Override
			public void keyPressed(KeyEvent e) { }

			@Override
			public void keyReleased(KeyEvent e) { }
        });
		txtSegundoNome.setColumns(10);
		txtSegundoNome.setBounds(108, 36, 148, 20);
		jPainelDados.add(txtSegundoNome);
		
		JLabel lblRaMilitar = new JLabel("RA Militar:");
		lblRaMilitar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRaMilitar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaMilitar.setBounds(10, 66, 93, 14);
		jPainelDados.add(lblRaMilitar);
		
		txtRaMilitar = new JTextField();
		((AbstractDocument) txtRaMilitar.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
				if (text.matches("\\d+")) {
					super.insertString(fb, offset, text, attr);
				}
			}
			
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				if (text.matches("\\d+")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});
		txtRaMilitar.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.getKeyChar())) { 
					if(txtRaMilitar.getText().length() >= 11) { 
						txtRaMilitar.setText(String.valueOf(txtRaMilitar.getText()).substring(0, 11));
						return;
					}
				}
			}
		});
		txtRaMilitar.setColumns(10);
		txtRaMilitar.setBounds(108, 63, 148, 20);
		jPainelDados.add(txtRaMilitar);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(247, 11, 64, 14);
		jPainelDados.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(308, 8, 127, 20);
		jPainelDados.add(txtEmail);
		
		rdMasculino = new JRadioButton("Masculino");
		rdMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdFeminino.isSelected()) { 
					rdFeminino.setSelected(false);
				}
			}
		});
		rdMasculino.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdMasculino.setBounds(267, 62, 83, 23);
		jPainelDados.add(rdMasculino);
		
		rdFeminino = new JRadioButton("Feminino");
		rdFeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdMasculino.isSelected()) { 
					rdMasculino.setSelected(false);
				}
			}
		});
		rdFeminino.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdFeminino.setBounds(352, 62, 83, 23);
		jPainelDados.add(rdFeminino);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(257, 39, 46, 14);
		jPainelDados.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(308, 36, 127, 20);
		jPainelDados.add(txtSenha);
		
		JPanel jPainelBotoes = new JPanel();
		jPainelBotoes.setBounds(10, 147, 448, 35);
		jPainelBotoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		jPainelBotoes.setBorder(Main.getBorder());
		cadastro.getContentPane().add(jPainelBotoes);
		jPainelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Button btConcluir = new Button("Concluir");
		btConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tudoPreenchido()) { 
					UsuarioDAO usuario = new UsuarioDAO(txtRaMilitar.getText(), txtPrimeiroNome.getText(), txtSegundoNome.getText(), txtEmail.getText(), String.valueOf(txtSenha.getPassword()), System.currentTimeMillis(), (rdFeminino.isSelected() ? 'f' : 'm'), Cargo.CORONEL, false, new ArrayList<String>());
					if(!usuario.jaExiste()) { 
						usuario.cadastrar();
						JOptionPane.showMessageDialog(null, "Cadastro concluido!");
						destroy();
						Login.call();
					} else { 
						JOptionPane.showMessageDialog(null, "Este RA Militar já está cadastrado em nosso banco de dados!");
					}
				} else { 
					JOptionPane.showMessageDialog(null, "Você precisa preencher todos os campos!");
				}
			}
		});
		jPainelBotoes.add(btConcluir);
		btConcluir.setFont(new Font("Dialog", Font.BOLD, 12));
		
		Button btCancelar = new Button("Cancelar");
		jPainelBotoes.add(btCancelar);
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destroy();
				Login.call();
			}
		});
		btCancelar.setFont(new Font("Dialog", Font.BOLD, 12));
	}
	
	public boolean tudoPreenchido() {
		return txtPrimeiroNome.getText() != null && txtSegundoNome.getText() != null && txtRaMilitar.getText() != null && txtEmail.getText() != null && txtSenha.getPassword() != null && rdFeminino.isSelected() || rdMasculino.isSelected(); 
	}
}
