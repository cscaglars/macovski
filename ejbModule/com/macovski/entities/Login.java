package com.macovski.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
public class Login extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4552184118771877252L;

	@Column(name = "Username", nullable = false)
	private String Username;
	
	@Column(name = "Password", nullable = false)
	private String Password;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	public Login() {
		// TODO Auto-generated constructor stub
	}
	
	public Login(String username, String password) {
		// TODO Auto-generated constructor stub
		
		Username = username;
		Password = password;		
	}
}
