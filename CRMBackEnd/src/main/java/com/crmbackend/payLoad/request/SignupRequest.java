package com.crmbackend.payLoad.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	
	@NotBlank
	@Size(min = 6,max = 64)
	private String firstname;

	@NotBlank
	@Size(min = 6,max = 64)
	private String lastname;

	@NotBlank
	@Size(min = 6, max = 128)
	private String username;

	@NotBlank
	@Size(min = 6, max = 128)
	private String password;

	@NotBlank
	@Size(min = 6, max = 128)
	@Email
	private String email;

	@NotBlank
	@Size(min = 10, max = 64)
	private String phone;

	@NotBlank
	@Size(max = 10)
	private Set<String> roles;
	
	

	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Set<String> getRoles() {
		return roles;
	}



	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}



	@Override
	public String toString() {
		return "SignupRequest [username=" + username + ", password=" + password + ", email=" + email + ", firstname="
				+ firstname + ", lastname=" + lastname + ", phone=" + phone + ", roles=" + roles + "]";
	}



}
