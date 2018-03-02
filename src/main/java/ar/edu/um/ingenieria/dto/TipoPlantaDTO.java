package ar.edu.um.ingenieria.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TipoPlantaDTO {
	private Integer id;

	@NotNull
	@Size(min=2,max=45)
	private String nombre;

	@NotNull
	@Size(min=2,max=255)
	private String descripcion;

	private List<PlantaDTO> plantasDTO;

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

	public List<PlantaDTO> getPlantasDTO() {
		return plantasDTO;
	}

	public void setPlantasDTO(List<PlantaDTO> plantasDTO) {
		this.plantasDTO = plantasDTO;
	}

	@Override
	public String toString() {
		return "TipoPlantaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", plantasDTO="
				+ plantasDTO + "]";
	}
}
