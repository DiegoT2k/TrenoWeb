package com.corso.vo;

import javax.validation.constraints.NotBlank;

public class LoginVO {

	@NotBlank(message = "Username è un campo obbligatorio")
	private String username;
	
	@NotBlank(message = "Password è un campo obbligatorio")
	private String password;
	
	
	public LoginVO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginVO() {

	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
