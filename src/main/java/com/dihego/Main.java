package com.dihego;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.dihego.construtor.UsuarioDAO;
import com.dihego.interfaces.Login;
import com.dihego.interfaces.Painel;

import lombok.Getter;
import lombok.Setter;

public class Main {
	
	@Getter
	static Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
	@Setter
	@Getter
	static UsuarioDAO usuario = null;
	@Getter
	private String enderecoSql = "localhost", usuarioSql = "root", senhaSql = "", databaseSql = "armazenamento";
	@Getter
	private int portaSql = 3306;
	
	public static void main(String[] args) {
		if(usuario == null) { 
			Login.call();
		} else { 
			Painel.call();
		}
	}
	
	public void criarTabelas() { 
		try {
			
		} catch (Exception e) {
		}
	}
	
	public static boolean isAutenticado()  {
		return usuario != null && usuario.isAutenticado();
	}
}
