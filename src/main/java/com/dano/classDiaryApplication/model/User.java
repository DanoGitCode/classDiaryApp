package com.dano.classDiaryApplication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.dano.classDiaryApplication.model.Role;;

@Entity
@Table(name = "auth_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_user_id")
	private int id;
	
	@Column(name = "first_name")
	private String name;

	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "Wprowadź Email")
	@Email(message = "Błędny email")
	@Column(name = "email")
	protected String email;
	
	@NotNull(message = "Wprowadź hasło")
	@Length(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
	@Column(name = "password")
	protected String password;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "uclass")
	private String uclass;
	
	@NotNull(message ="Wybierz rolę")
	@Column(name="u_role")
	private int uRole;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUclass() {
		return uclass;
	}

	public void setUclass(String uclass) {
		this.uclass = uclass;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getuRole() {
		return uRole;
	}

	public void setuRole(int uRole) {
		this.uRole = uRole;
	}
	
	
}
