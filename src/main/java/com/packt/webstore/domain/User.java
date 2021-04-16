package com.packt.webstore.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3716314526541159697L;
	private String username;
	private String email;
	private String password;
	private LocalDateTime create_time;
	private List<String> roles;
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreate_time() {
		return create_time;
	}
	public void setCreate_time(LocalDateTime create_time) {
		this.create_time = create_time;
	}
	
}
