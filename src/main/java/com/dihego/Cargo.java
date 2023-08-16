package com.dihego;

import lombok.Getter;

@Getter
public enum Cargo {
	
	CORONEL("Coronel"),
	TENENTE_CORONEL("Tenente"),
	MAJOR("Major");
	
	String nomePatente;
	
	private Cargo(String nomePatente) {
		this.nomePatente = nomePatente;
	}
}
