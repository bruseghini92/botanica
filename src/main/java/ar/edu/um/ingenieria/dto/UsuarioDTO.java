package ar.edu.um.ingenieria.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
public class UsuarioDTO {
	
	private Integer id;	
	@NotNull
	private String user;	
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min=8,max=16)
	private String password;
	@NotNull
	@Size(min=2,max=120)
	private String nombre;
	@NotNull
	@Size(min=2,max=120)
	private String apellido;
	@NotNull
	private Integer edad;
	private RolDTO rol;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
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


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public RolDTO getRol() {
		return rol;
	}


	public void setRol(RolDTO rol) {
		this.rol = rol;
	}
	

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", user=" + user + ", email=" + email + ", password=" + password + ", nombre="
				+ nombre + ", apellido=" + apellido + ", edad=" + edad + ", rol=" + rol + "]";
	}


	public UsuarioDTO() {
		super();
	}	
}
