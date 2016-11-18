package Accesos;

import org.apache.commons.codec.digest.DigestUtils;

@javax.persistence.Entity
@javax.persistence.Table(name = "Usuarios")
public class Usuario {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue
	long id;

	@javax.persistence.Column(unique = true, nullable = false)
	String username;
	@javax.persistence.Column(name="pass",nullable = false)
	String password;
	@javax.persistence.Column(nullable = false)
	String role;

	public Usuario(String name, String pass, String role) {
		this.username = name;
		this.password = DigestUtils.sha1Hex(pass);
		this.role = role;
	}

	public Usuario(){
		
	}
	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}
}
