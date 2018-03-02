package ar.edu.um.ingenieria.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SueloDTO {
	private Integer id;	
	@NotNull
	@Size(min=3)
	private String nombre;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "SueloDTO [id=" + id + ", nombre=" + nombre + "]";
	}
	public SueloDTO() {
		super();
	}
}
