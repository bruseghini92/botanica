package ar.edu.um.ingenieria.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EstadoDTO {
	
	private Integer id;
	@NotNull
	@Size(min=2,max=45)	
	private String nombre;
	@NotNull
	@Size(min=2,max=512)	
	private String descripcion;
	
	private List<SeguimientoDTO> seguimientosDTO;
	
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
	public List<SeguimientoDTO> getSeguimientos() {
		return seguimientosDTO;
	}
	public void setSeguimientos(List<SeguimientoDTO> seguimientosDTO) {
		this.seguimientosDTO = seguimientosDTO;
	}
	@Override
	public String toString() {
		return "EstadoDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", seguimientosDTO="
				+ seguimientosDTO + "]";
	}
	public EstadoDTO() {
		super();
	}	 
}
