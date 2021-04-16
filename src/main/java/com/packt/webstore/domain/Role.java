package com.packt.webstore.domain;

public enum Role {
	US("USER"),
	AD("ADMIN"),
	SR("SERVICE");
	
	public final String label;
	
	private Role(String label) {
		this.label = label;
	}
}
