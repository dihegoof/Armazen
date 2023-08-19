package com.dihego.gerenciadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dihego.Main;
import com.dihego.construtor.UsuarioDAO;

public class UsuarioGerenciador {
	
	public static boolean jaExiste(UsuarioDAO usuario) { 
		try {
			PreparedStatement stmt = (PreparedStatement) Main.getMySql().getConexao().createStatement();
			stmt.executeUpdate("SELECT * FROM `usuarios` WHERE `raMilitar` = ?");
			stmt.setString(1, usuario.getRaMilitar());
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao buscar usuário! ", e.getLocalizedMessage());
		}
		return false;
	}
}
