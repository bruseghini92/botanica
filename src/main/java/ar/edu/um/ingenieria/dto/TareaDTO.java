package ar.edu.um.ingenieria.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TareaDTO {
	
	private Integer id;

	@NotNull
	@Size(min=2,max=120)
	private String nombre;

	@NotNull
	@Size(min=2,max=255)
	private String descripcion;

	private EtapaDTO etapaDTO;

	private List<SeguimientoDTO> seguimientoDTO;

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
		return etapaDTO;
	}

	public void setEtapaDTO(EtapaDTO etapaDTO) {
		this.etapaDTO = etapaDTO;
	}

	public List<SeguimientoDTO> getSeguimientoDTO() {
		return seguimientoDTO;
	}

	public void setSeguimientoDTO(List<SeguimientoDTO> seguimientoDTO) {
		this.seguimientoDTO = seguimientoDTO;
	}

	@Override
	public String toString() {
		return "TareaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", etapaDTO=" + etapaDTO
				+ ", seguimientoDTO=" + seguimientoDTO + "]";
	}
	
}
