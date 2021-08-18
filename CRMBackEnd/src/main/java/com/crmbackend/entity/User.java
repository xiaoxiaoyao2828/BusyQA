package com.crmbackend.entity;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "tblUsers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Username", length = 128, nullable = false, unique = true)
	private String username;
	@Column(name = "FirstName", nullable = false, length = 45)
	private String firstName;
	@Column(name = "LastName", nullable = false, length = 45)
	private String lastName;
	@Column(name = "Password", length = 64, nullable = false)
	private String password;
	@Column(name = "Email", length = 128, nullable = false, unique = true)
	private String email;
	@Column(name = "Phone", length = 64, nullable = false, unique = true)
	private String phoneNumber;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	@JsonIgnoreProperties("user")

	@OneToMany(mappedBy = "user")
	private Set<TeamUsers> team_users = new HashSet<>();

	public User() {

	}

	public User(String username, String firstName, String lastName, String password, String email, String phoneNumber) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role roles) {
		this.roles.add(roles);
	}

	@Transient
	public String getFullName() {
		return firstName + " " + lastName;
	}

	public User(Integer id, String username, String firstName, String lastName, String email, String phoneNumber,
			Set<Role> roles, Set<TeamUsers> team_users) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
		this.team_users = team_users;
	}

	public Set<TeamUsers> getTeam_users() {
		return team_users;
	}

	public void setTeam_users(Set<TeamUsers> team_users) {
		this.team_users = team_users;
	}

}