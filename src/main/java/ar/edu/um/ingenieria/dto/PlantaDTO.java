package ar.edu.um.ingenieria.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class PlantaDTO {

	private Integer id;
	
	@NotNull
	@Size(min = 2, max = 45)
	private String nombre;

	@NotNull
	@Size(min = 2, max = 255)
	private String descripcion;

	private TipoPlantaDTO tipo;

	private TemporadaDTO temporada;

	private SueloDTO suelo;

	private ClimaDTO clima;

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date tiempoRiego;

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

	public TipoPlantaDTO getTipo() {
		return tipo;
	}

	public void setTipo(TipoPlantaDTO tipo) {
		this.tipo = tipo;
	}

	public TemporadaDTO getTemporada() {
		return temporada;
	}

	public void setTemporada(TemporadaDTO temporada) {
		this.temporada = temporada;
	}

	public SueloDTO getSuelo() {
		return suelo;
	}

	public void setSuelo(SueloDTO suelo) {
		this.suelo = suelo;
	}

	public ClimaDTO getClima() {
		return clima;
	}

	public void setClima(ClimaDTO clima) {
		this.clima = clima;
	}

	public Date getTiempoRiego() {
		return tiempoRiego;
	}

	public void setTiempoRiego(Date tiempoRiego) {
		this.tiempoRiego = tiempoRiego;
	}

	@Override
	public String toString() {
		return "PlantaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", temporada=" + temporada + ", suelo=" + suelo + ", clima=" + clima + ", tiempoRiego=" + tiempoRiego
				+ "]";
	}

	public PlantaDTO() {
		super();
	}
}
