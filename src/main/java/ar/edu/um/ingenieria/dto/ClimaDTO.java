package ar.edu.um.ingenieria.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClimaDTO {

	private Integer id;
	@NotNull
	@Size(min=3,max=45)
	private String nombre;
	
	@Override
	public String toString() {
		return "ClimaDTO [id=" + id + ", nombre=" + nombre + "]";
	}
	
	public ClimaDTO() {
		super();
	}
}
