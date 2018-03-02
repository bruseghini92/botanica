package ar.edu.um.ingenieria.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonaDTO {
	
	private Integer id;
	
	@NotNull
	@Size(min=2,max=50)	
	private String apellido;
	@NotNull
	@Size(min=2,max=50)	
	private String nombre;
	@NotNull
	@Size(min=1,max=2)
	private Integer edad;
	@NotNull
	private UsuarioDTO usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "PersonaDTO [id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + ", edad=" + edad
				+ ", usuario=" + usuario + "]";
	}

	public PersonaDTO() {
		super();
	}	
}
