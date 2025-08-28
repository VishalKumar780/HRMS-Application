package com.example.hrms.dto;
import java.util.Set;


public class RegisterDTO{
    private String email;
    private String password;
    private Set<String> roles;  // e.g., ["ADMIN"], ["EMPLOYEE"], ["HR"]
    
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
