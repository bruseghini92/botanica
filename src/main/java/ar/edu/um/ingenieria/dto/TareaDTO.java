package ar.edu.um.ingenieria.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TareaDTO {

	private Integer id;

	@NotNull
	@Size(min = 2, max = 120)
	private String nombre;

	@NotNull
	@Size(min = 2, max = 255)
	private String descripcion;

	private EtapaDTO etapa;

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

	public EtapaDTO getEtapaDTO() {
		return etapa;
	}

	public void setEtapaDTO(EtapaDTO etapaDTO) {
		this.etapa = etapaDTO;
	}

	@Override
	public String toString() {
		return "TareaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", etapaDTO=" + etapa
				+ "]";
	}

}
