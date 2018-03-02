package ar.edu.um.ingenieria.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class PlantaDTO {
	
	private Integer id;
	@NotNull
	@Size(min=2,max=45)
	private String nombre;
	@NotNull 
	private Date mesInicio;
	@NotNull 
	private Date mesFin;
	@NotNull 	
	@Size(max=512)
	private String descripcion;
	@NotNull 
	private SueloDTO suelo;
	@NotNull 
	private ClimaDTO clima;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getMesInicio() {
		return mesInicio;
	}
	public void setMesInicio(Date mesInicio) {
		this.mesInicio = mesInicio;
	}
	public Date getMesFin() {
		return mesFin;
	}
	public void setMesFin(Date mesFin) {
		this.mesFin = mesFin;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	@Override
	public String toString() {
		return "PlantaDTO [id=" + id + ", mesInicio=" + mesInicio + ", mesFin=" + mesFin + ", descripcion="
				+ descripcion + ", suelo=" + suelo + ", clima=" + clima + "]";
	}
	public PlantaDTO() {
		super();
	}
}
