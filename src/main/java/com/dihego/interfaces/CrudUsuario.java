package com.dihego.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;

import lombok.Getter;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.dihego.Cargo;
import com.dihego.Main;
import com.dihego.construtor.UsuarioDAO;
import com.dihego.utils.TimeManager;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CrudUsuario {

	@Getter
	public static CrudUsuario instance = new CrudUsuario();

	static boolean searched = false;
	
	private JFrame painelUsuario;
	private JTextField txtRaMilitar;
	private final JComboBox<String> cbCargo = new JComboBox<String>();
	private JTextField txtPermissao;

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
		((AbstractDocument) txtRaMilitar.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
				if (text != null && text.matches("\\d+")) {
					super.insertString(fb, offset, text, attr);
				}
			}
			
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				if (text != null && text.matches("\\d+")) {
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
		txtRaMilitar.setBounds(75, 59, 115, 20);
		jPainelCrud.add(txtRaMilitar);
		txtRaMilitar.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cargo:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(200, 59, 50, 20);
		jPainelCrud.add(lblNewLabel_1_1);
		cbCargo.setBounds(244, 59, 93, 20);
		jPainelCrud.add(cbCargo);
		
		final JLabel lblNome = new JLabel("Nome: %n");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(10, 10, 327, 20);
		lblNome.setText(lblNome.getText().replace("%n", "???"));
		jPainelCrud.add(lblNome);
		
		final JLabel lblUltimoAcesso = new JLabel("Último acesso há %t");
		lblUltimoAcesso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUltimoAcesso.setBounds(10, 33, 327, 20);
		lblUltimoAcesso.setText(lblUltimoAcesso.getText().replace("%t", "???"));
		jPainelCrud.add(lblUltimoAcesso);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nova permissão:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(10, 85, 105, 20);
		jPainelCrud.add(lblNewLabel_1_2);
		
		txtPermissao = new JTextField();
		txtPermissao.setColumns(10);
		txtPermissao.setBounds(115, 85, 222, 20);
		jPainelCrud.add(txtPermissao);
		
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
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.isAutenticado()) { 
					if(txtRaMilitar.getText() != null) { 
						UsuarioDAO usuario = getUser(txtRaMilitar.getText());
						if(usuario != null) { 
							lblNome.setText(lblNome.getText().replace("???", usuario.getPrimeiroNome() + " " + usuario.getUltimoNome()));
							lblUltimoAcesso.setText(lblUltimoAcesso.getText().replace("???", TimeManager.getInstance().formatDifference(usuario.getUltimoAcesso() - System.currentTimeMillis())));
							
							for(Cargo cargos : Cargo.values()) { 
								cbCargo.addItem(cargos.getNomePatente());
							}
							
							searched = true;
							
							cbCargo.setEnabled(true);
							txtRaMilitar.setEnabled(false);
						} else { 
							JOptionPane.showMessageDialog(null, "Não foi encontrado algum usuário com este RA Militar!");
						}
					} else { 
						JOptionPane.showMessageDialog(null, "Você precisa preencher o campo do RA Militar!");
					}
				}
			}
		});
		btBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPainelBotoes.add(btBuscar);
		
		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.isAutenticado()) { 
					if(searched) { 
						UsuarioDAO usuario = getUser(txtRaMilitar.getText());
						if(usuario == null) return;
						if(!txtRaMilitar.getText().equals(Main.getUsuario().getRaMilitar())) { 
							if(delete(usuario.getRaMilitar())) { 
								JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
								
								for(Cargo cargos : Cargo.values()) { 
									cbCargo.removeItem(cargos.getNomePatente());
								}
								
								cbCargo.setEnabled(false);
								txtRaMilitar.setEnabled(true);
								
								txtRaMilitar.getText();
								txtPermissao.setText(null);
								
								searched = false;
							} else { 
								JOptionPane.showMessageDialog(null, "O usuário não foi deletado!");
							}
						} else { 
							JOptionPane.showMessageDialog(null, "Você não pode excluir seu proprio cadastro!");
						}
					} else { 
						JOptionPane.showMessageDialog(null, "Você precisa preencher o campo do RA Militar!");
					}
				}
			}
		});
		btExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPainelBotoes.add(btExcluir);
		
		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.isAutenticado()) { 
					if(searched) { 
						UsuarioDAO usuario = getUser(txtRaMilitar.getText());
						if(usuario == null) return;
						usuario.setCargo(Cargo.get(cbCargo.getSelectedItem().toString()));
						if(txtPermissao.getText().length() > 0) { 
							Main.debug("Contém");
							List<String> permissoes = usuario.getPermissoes();
							if(!permissoes.contains(txtPermissao.getText())) { 
								permissoes.add(txtPermissao.getText() + ",");
								usuario.setPermissoes(permissoes);
							} else { 
								JOptionPane.showMessageDialog(null, "Este usuário já possui esta permissão!");
							}
							txtPermissao.setText(null);
						} else { 
							Main.debug("Nulo");
						}
						usuario.salvar();
						JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
					} else { 
						JOptionPane.showMessageDialog(null, "Você precisa preencher o campo do RA Militar!");
					}
				}
			}
		});
		btSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jPainelBotoes.add(btSalvar);
		
		cbCargo.setEnabled(false);
	}
	
	public UsuarioDAO getUser(String raMilitar) { 
		UsuarioDAO usuario = null;
		try {
			Statement stmt = Main.getMySql().getConexao().createStatement();
			stmt.executeQuery("SELECT * FROM `usuarios` WHERE `raMilitar` = '" + raMilitar + "'");
			ResultSet rs = stmt.getResultSet();
			if(rs.next()) { 
				usuario = new UsuarioDAO(raMilitar, rs.getString("primeiroNome"), rs.getString("ultimoNome"), rs.getString("email"), rs.getString("senha"), rs.getLong("ultimoAcesso"), 'm', Cargo.valueOf(rs.getString("cargo")), false, new ArrayList<String>());
				if(rs.getString("permissoes").toCharArray().length > 2) {
					List<String> permissoes = new ArrayList<>();
					for(String names : rs.getString("permissoes").split(",")) { 
						permissoes.add(names);
					}
					usuario.setPermissoes(permissoes);
				}
			}
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao carregar usuário!", e.getMessage());
		}
		return usuario;
	}
	
	public boolean delete(String raMilitar) { 
		try {
			Statement stmt = Main.getMySql().getConexao().createStatement();
			stmt.executeUpdate("DELETE FROM `usuarios` WHERE `raMilitar` = '" + raMilitar + "'");
			return true;
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao excluir usuário!", e.getMessage());
		}
		return false;
	}
}
