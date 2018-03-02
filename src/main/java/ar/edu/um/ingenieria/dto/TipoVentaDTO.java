package ar.edu.um.ingenieria.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoVentaDTO {
	
	private Integer id;
	
	@NotNull
	@Size(min=2,max=45)
	private String nombre;
	
	@NotNull
	@Size(min=2,max=255)
	private String descripcion;
	
	private List<VentaDTO> ventasDTO;

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

	public List<VentaDTO> getVentasDTO() {
		return ventasDTO;
	}

	public void setVentasDTO(List<VentaDTO> ventasDTO) {
		this.ventasDTO = ventasDTO;
	}

	@Override
	public String toString() {
		return "TipoVentaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", ventasDTO="
				+ ventasDTO + "]";
	}
}
