package com.dihego;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.dihego.construtor.UsuarioDAO;
import com.dihego.interfaces.Login;
import com.dihego.interfaces.Painel;
import com.dihego.sql.MySql;

import lombok.Getter;
import lombok.Setter;

public class Main {
	
	@Getter
	static Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
	@Setter
	@Getter
	static UsuarioDAO usuario = null;
	@Getter
	static MySql mySql = null;
	@Getter
	static boolean debug = true;
	
	public static void main(String[] args) {
		mySql = new MySql("localhost", "root", "", "armazenamento");
		if(usuario == null) { 
			Login.call();
		} else { 
			Painel.call();
		}
	}
	
	public static boolean isAutenticado()  {
		return usuario != null && usuario.isAutenticado();
	}
	
	public static void debug(String... mensagens) { 
		if(!debug) return;
		for(String linha : mensagens) { 
			System.out.println(linha);
		}
	}
	
	public static boolean hasApagar(int code) { 
		return code == 8;
	}
}
