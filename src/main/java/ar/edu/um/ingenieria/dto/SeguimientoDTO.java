package ar.edu.um.ingenieria.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SeguimientoDTO {

	private Integer id;

	private UsuarioDTO usuario;

	@NotNull
	private PlantaDTO planta;

	@NotNull
	private EstadoDTO estado;

	private EtapaDTO etapa;
	
	private TareaDTO tarea;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date ultimoRiego;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date proximoRiego;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaInicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaAbono;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaPoda;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaCosecha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public PlantaDTO getPlanta() {
		return planta;
	}

	public void setPlanta(PlantaDTO planta) {
		this.planta = planta;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estadoDTO) {
		this.estado = estadoDTO;
	}

	public EtapaDTO getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaDTO etapaDTO) {
		this.etapa = etapaDTO;
	}

	public TareaDTO getTarea() {
		return tarea;
	}

	public void setTarea(TareaDTO tareaDTO) {
		this.tarea = tareaDTO;
	}

	public Date getUltimoRiego() {
		return ultimoRiego;
	}

	public void setUltimoRiego(Date ultimoRiego) {
		this.ultimoRiego = ultimoRiego;
	}

	public Date getProximoRiego() {
		return proximoRiego;
	}

	public void setProximoRiego(Date proximoRiego) {
		this.proximoRiego = proximoRiego;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaAbono() {
		return fechaAbono;
	}

	public void setFechaAbono(Date fechaAbono) {
		this.fechaAbono = fechaAbono;
	}

	public Date getFechaPoda() {
		return fechaPoda;
	}

	public void setFechaPoda(Date fechaPoda) {
		this.fechaPoda = fechaPoda;
	}

	public Date getFechaCosecha() {
		return fechaCosecha;
	}

	public void setFechaCosecha(Date fechaCosecha) {
		this.fechaCosecha = fechaCosecha;
	}

	@Override
	public String toString() {
		return "SeguimientoDTO [id=" + id + ", usuario=" + usuario + ", planta=" + planta + ", estado=" + estado
				+ ", etapa=" + etapa + ", tarea=" + tarea + ", ultimoRiego=" + ultimoRiego + ", proximoRiego="
				+ proximoRiego + ", fechaInicio=" + fechaInicio + ", fechaAbono=" + fechaAbono + ", fechaPoda="
				+ fechaPoda + ", fechaCosecha=" + fechaCosecha + "]";
	}
}
