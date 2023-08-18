package com.dihego.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class MySql {
	
	String address, user, password, database;
	Connection conn;

	public MySql(String address, String user, String password, String database) {
		this.address = address;
		this.user = user;
		this.password = password;
		this.database = database;
		open();
	}
	
	public void open() {
		try {
			Connection conexao = DriverManager.getConnection("jdbc:mysql://" + address + ":3306/" + database, user, password);
			if(conn == null) {
				this.conn = conexao;
			}
			create();
		} catch (Exception e) {
		}
	}
	
	public void close() { 
		try {
			if(conn != null) {
				this.conn.close();
			}
		} catch (Exception e) {
		}
	}
	
	public void create() {
		List<String> list = Arrays.asList(
				"CREATE TABLE `login` (`cpf` VARCHAR(32) NOT NULL, `senha` VARCHAR(32) NOT NULL);",
				"CREATE TABLE `cliente` (`primeironome` VARCHAR(32) NOT NULL, `ultimonome` VARCHAR(32) NOT NULL, `celular` VARCHAR(32) NOT NULL, `cpf` VARCHAR(32) NOT NULL, `rg` VARCHAR(32) NOT NULL, `datanascimento` INT, `email` VARCHAR(64) NOT NULL);",
				"CREATE TABLE `produto` (`nome` VARCHAR(64) NOT NULL, `marca` VARCHAR(32) NOT NULL, `tipo` VARCHAR(32) NOT NULL, `sabor` VARCHAR(32) NOT NULL, `preco` LONG, `descricao` TEXT);"
		);
		try {
			Statement stmt = getConn().createStatement();
			for(String names : list) { 
				stmt.executeUpdate(names);
			}
		} catch (Exception e) {
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
