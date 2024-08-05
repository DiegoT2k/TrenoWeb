package com.corso.vo;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationVO {
	
	@NotBlank(message = "Il nome è obbligatorio")
	@Size(max = 50, message = "Il nome non può superare i 50 caratteri")
	@Pattern(regexp = "^[a-zA-ZÀ-ÿ ']+$", message = "Il nome può contenere solo lettere")
	private String nome;
	
	@NotBlank(message = "Il cognome è obbligatorio")
	@Size(max = 50, message = "Il cognome non può superare i 50 caratteri")
	@Pattern(regexp = "^[a-zA-ZÀ-ÿ ']+$", message = "Il cognome può contenere solo lettere")
	private String cognome;
	
	@NotBlank(message = "L\'username è obbligatorio")
	@Size(min = 4, max = 15, message = "L\'username deve essere lungo tra i 4 e i 15 caratteri")
	private String username;
	
	@NotBlank(message = "L\'email è obbligatoria")
	@Email(message = "Deve essere un indirizzo email valido")
	private String email;
	
	@NotBlank(message = "La password è obbligatoria")
	@Size(min = 8, max = 20, message = "La password deve avere una lunghezza minima di 8 caratteri e massimo 20 caratteri")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
	         message = "La password deve contenere almeno una lettera maiuscola, una lettera minuscola, un numero e un carattere speciale")
	private String password;
	
	@NotBlank(message = "La conferma della password è obbligatoria")
	@Size(min = 8, max = 20, message = "La conferma della password deve avere una lunghezza minima di 8 caratteri e massimo 20 caratteri")
	private String confirmPassword;
	
	@AssertTrue(message = "Le password devono coincidere")
    private boolean isPasswordsMatching() {
        return password != null && password.equals(confirmPassword);
    }
	
	public RegistrationVO() {
		
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	
}
