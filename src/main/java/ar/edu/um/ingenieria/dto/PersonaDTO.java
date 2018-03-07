package ar.edu.um.ingenieria.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class PersonaDTO {
	
	private Integer id;
	
	@NotNull
	@Size(min=2,max=50)	
	private String apellido;
	
	@NotNull
	@Size(min=2,max=50)	
	private String nombre;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaNacimiento;
	
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "PersonaDTO [id=" + id + ", apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + "]";
	}

	public PersonaDTO() {
		super();
	}	
}
