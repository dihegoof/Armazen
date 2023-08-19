package com.dihego.construtor;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.dihego.Cargo;
import com.dihego.Main;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDAO {
	
	String raMilitar, primeiroNome, ultimoNome, email, senha;
	long ultimoAcesso;
	char sexo;
	Cargo cargo;
	boolean autenticado;
	List<String> permissoes;
	
	public UsuarioDAO(String raMilitar, String primeiroNome, String ultimoNome, String email, String senha, long ultimoAcesso, char sexo, Cargo cargo, boolean autenticado, List<String> permissoes) {
		this.raMilitar = raMilitar;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.senha = senha;
		this.ultimoAcesso = ultimoAcesso;
		this.sexo = sexo;
		this.cargo = cargo;
		this.autenticado = autenticado;
		this.permissoes = permissoes;
	}
	
	public String getNomeLogado() { 
		String nome = (getSexo() == 'm' ? "Sr. " : "Sra.");
		return nome + getPrimeiroNome();
	}
	
	public void cadastrar() { 
		try {
			Statement stmt = Main.getMySql().getConexao().createStatement();
			stmt.executeUpdate("INSERT INTO `usuarios` (`raMilitar`, `primeiroNome`, `ultimoNome`, `email`, `senha`, `ultimoAcesso`, `sexo`, `cargo`, `permissoes`) VALUES ('" + getRaMilitar() + "', '" + getPrimeiroNome() + "', '" + getUltimoNome() + "', '" + getEmail() + "', '" + getSenha() + "', '" + getUltimoAcesso() +"', '" + String.valueOf(getSexo()) + "', '" + getCargo().toString() + "', '" + getPermissoes().toString().replace("[", "").replace("]", "") +"')");
			Main.debug("Usuário criado!");
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao criar o usuário!", e.getMessage());
		}
	}
	
	public void salvar() {
		try {
			Statement stmt = Main.getMySql().getConexao().createStatement();
			stmt.executeUpdate("UPDATE `usuarios` SET `email` = '" + getEmail() + "', `senha` = '" + getSenha() + "', `ultimoAcesso` = '" + getUltimoAcesso()+ "', `cargo` = '" + getCargo().toString() + "', `permissoes` = '" + getPermissoes().toString().replace("[", "").replace("]", "") + "' WHERE `raMilitar` = '" + getRaMilitar() + "'");
			Main.debug("Usuário salvo!");
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao salvar o usuário!", e.getMessage());
		}
	}
	
	public boolean jaExiste() { 
		try {
			Statement stmt = Main.getMySql().getConexao().createStatement();
			stmt.executeQuery("SELECT * FROM `usuarios` WHERE `raMilitar` = '" + getRaMilitar() + "'");
			ResultSet rs = stmt.getResultSet();
			return rs.next();
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao buscar usuário! ", e.getMessage());
		}
		return false;
	}
}
