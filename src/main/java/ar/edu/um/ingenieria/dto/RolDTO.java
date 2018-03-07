package ar.edu.um.ingenieria.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RolDTO {

	private Integer id;

	@NotNull
	@Size(min = 5, max = 520)
	private String rol;

	@NotNull
	@Size(min = 5, max = 520)
	private String descripcion;

	public RolDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "RolDTO [id=" + id + ", rol=" + rol + ", descripcion=" + descripcion + "]";
	}
}
