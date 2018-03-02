package ar.edu.um.ingenieria.dto;

import java.sql.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class PlantaDTO {
	
	private Integer id;
	@NotNull
	@Size(min=2,max=45)
	private String nombre;

	@NotNull
	@Size(min=2,max=255)
	private String descripcion;

	private TipoPlantaDTO tipoDTO;

	private TemporadaDTO temporadaDTO;

	private SueloDTO sueloDTO;

	private ClimaDTO climaDTO;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date tiempoRiego;
	
	private List<SeguimientoDTO> seguimientoDTO;
	
	@Override
	public String toString() {
		return "PlantaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", temporadaDTO="
				+ temporadaDTO + ", sueloDTO=" + sueloDTO + ", climaDTO=" + climaDTO + ", tiempoRiego=" + tiempoRiego
				+ ", seguimientoDTO=" + seguimientoDTO + "]";
	}

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


	public TipoPlantaDTO getTipoDTO() {
		return tipoDTO;
	}


	public void setTipoDTO(TipoPlantaDTO tipoDTO) {
		this.tipoDTO = tipoDTO;
	}


	public TemporadaDTO getTemporadaDTO() {
		return temporadaDTO;
	}


	public void setTemporadaDTO(TemporadaDTO temporadaDTO) {
		this.temporadaDTO = temporadaDTO;
	}


	public SueloDTO getSueloDTO() {
		return sueloDTO;
	}


	public void setSueloDTO(SueloDTO sueloDTO) {
		this.sueloDTO = sueloDTO;
	}


	public ClimaDTO getClimaDTO() {
		return climaDTO;
	}


	public void setClimaDTO(ClimaDTO climaDTO) {
		this.climaDTO = climaDTO;
	}


	public Date getTiempoRiego() {
		return tiempoRiego;
	}


	public void setTiempoRiego(Date tiempoRiego) {
		this.tiempoRiego = tiempoRiego;
	}


	public List<SeguimientoDTO> getSeguimientoDTO() {
		return seguimientoDTO;
	}


	public void setSeguimientoDTO(List<SeguimientoDTO> seguimientoDTO) {
		this.seguimientoDTO = seguimientoDTO;
	}


	public PlantaDTO() {
		super();
	}
}
