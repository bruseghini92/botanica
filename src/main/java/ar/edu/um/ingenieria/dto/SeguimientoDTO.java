package ar.edu.um.ingenieria.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class SeguimientoDTO {
	
	private Integer id;

	private UsuarioDTO usuarioDTO;

	private PlantaDTO plantaDTO;

	private EstadoDTO estadoDTO;
	
	private EtapaDTO etapaDTO;

	private TareaDTO tareaDTO;

	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date ultimoRiego;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date proximoRiego;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaInicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaAbono;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaPoda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public PlantaDTO getPlantaDTO() {
		return plantaDTO;
	}

	public void setPlantaDTO(PlantaDTO plantaDTO) {
		this.plantaDTO = plantaDTO;
	}

	public EstadoDTO getEstadoDTO() {
		return estadoDTO;
	}

	public void setEstadoDTO(EstadoDTO estadoDTO) {
		this.estadoDTO = estadoDTO;
	}

	public EtapaDTO getEtapaDTO() {
		return etapaDTO;
	}

	public void setEtapaDTO(EtapaDTO etapaDTO) {
		this.etapaDTO = etapaDTO;
	}

	public TareaDTO getTareaDTO() {
		return tareaDTO;
	}

	public void setTareaDTO(TareaDTO tareaDTO) {
		this.tareaDTO = tareaDTO;
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

	@Override
	public String toString() {
		return "SeguimientoDTO [id=" + id + ", usuarioDTO=" + usuarioDTO + ", plantaDTO=" + plantaDTO + ", estadoDTO="
				+ estadoDTO + ", ultimoRiego=" + ultimoRiego + ", proximoRiego=" + proximoRiego + ", fechaInicio="
				+ fechaInicio + ", fechaAbono=" + fechaAbono + ", fechaPoda=" + fechaPoda + "]";
	}
}
