package com.dihego.construtor;

import java.util.List;

import com.dihego.Cargo;

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
}
