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
	
	public static void main(String[] args) {
		if(usuario == null) { 
			Login.call();
		} else { 
			Painel.call();
		}
	}
	
	public static boolean isAutenticado()  {
		return usuario != null && usuario.isAutenticado();
	}
}
