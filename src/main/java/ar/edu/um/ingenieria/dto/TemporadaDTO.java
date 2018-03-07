package ar.edu.um.ingenieria.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class TemporadaDTO {
	private Integer id;

	@NotNull
	@Size(min = 2, max = 25)
	private String nombre;

	@NotNull
	@Size(min = 2, max = 255)
	private String descripcion;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaInicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaFin;

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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "TemporadaDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + "]";
	}

}
