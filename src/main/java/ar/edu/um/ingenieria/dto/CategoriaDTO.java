package ar.edu.um.ingenieria.dto;

import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoriaDTO {
	private Integer id;
	@NotNull
	@Size(min=3,max=45)
	private String nombre;
	
	@Size(min=3,max=255)
	@Column(name = "descripcion")
	private String descripcion;	
	
	private List<TemaDTO> temasDTO;
	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<TemaDTO> getTemasDTO() {
		return temasDTO;
	}

	public void setTemasDTO(List<TemaDTO> temasDTO) {
		this.temasDTO = temasDTO;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", temasDTO="
				+ temasDTO + "]";
	}
	
	public CategoriaDTO() {
		super();
	}
}
