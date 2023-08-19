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
	
	public static Cargo get(String name) { 
		for(Cargo cargos : values()) { 
			if(cargos.getNomePatente().equals(name)) { 
				return cargos;
			}
		}
		return null;
	}
}
