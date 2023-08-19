package com.dihego.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.dihego.Main;

import lombok.Getter;
import lombok.Setter;

public class MySql {
	
	@Getter
	@Setter
	String enderecoSql, usuarioSql, senhaSql, databaseSql;
	@Getter
	Connection conexao;
	List<String> tabelas = Arrays.asList(
			"CREATE TABLE IF NOT EXISTS `usuarios` (`raMilitar` VARCHAR(12) NOT NULL, `primeiroNome` VARCHAR(32) NOT NULL, `ultimoNome` VARCHAR(32) NOT NULL, `email` VARCHAR(32) NOT NULL, `senha` VARCHAR(32) NOT NULL, `ultimoAcesso` LONG, `sexo` VARCHAR(1) NOT NULL, `cargo` VARCHAR(32) NOT NULL, `permissoes` TEXT);"
			);
	
	public MySql(String enderecoSql, String usuarioSql, String senhaSql, String databaseSql) {
		this.enderecoSql = enderecoSql;
		this.usuarioSql = usuarioSql;
		this.senhaSql = senhaSql;
		this.databaseSql = databaseSql;
		iniciarConexao();
	}
	
	public void iniciarConexao() {
		try {
			Connection conexao = DriverManager.getConnection("jdbc:mysql://" + this.enderecoSql + ":3306/" + this.databaseSql, this.usuarioSql, this.senhaSql);
			Main.debug("Conexão iniciada!");
			this.conexao = conexao;
			criarTabelas();
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao iniciar a conexão!", e.getLocalizedMessage());
		}
	}
	
	public void fecharConexao() { 
		try {
			if(conexao != null) 
				this.conexao.close();
		} catch (Exception e) {
		}
	}
	
	public void criarTabelas() {
		try {
			Statement stmt = this.conexao.createStatement();
			for(String linhas : tabelas) { 
				stmt.executeUpdate(linhas);
			}
			Main.debug("Tabelas criadas!");
		} catch (Exception e) {
			Main.debug("Ocorreu um erro ao criar as tabelas!", e.getLocalizedMessage());
		}
	}
}
