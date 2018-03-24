package ar.edu.um.ingenieria.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "seguimientos")
public class Seguimiento implements Serializable {
	private static final long serialVersionUID = -7153249297528005760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planta_id")
	private Planta planta;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "etapa_id")
	private Etapa etapa;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tarea_id")
	private Tarea tarea;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "ultimo_riego")
	private Date ultimoRiego;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "proximo_riego")
	private Date proximoRiego;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_inicio")
	private Date fechaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_abono")
	private Date fechaAbono;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_poda")
	private Date fechaPoda;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "fecha_cosecha")
	private Date fechaCosecha;

	public Integer getId() {
		return id;
	}

	public Date getFechaCosecha() {
		return fechaCosecha;
	}

	public void setFechaCosecha(Date fechaCosecha) {
		this.fechaCosecha = fechaCosecha;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Date getUltimoRiego() {
		return ultimoRiego;
	}

	public void setUltimoRiego(Date ultimoRiego) {
		this.ultimoRiego = ultimoRiego;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((etapa == null) ? 0 : etapa.hashCode());
		result = prime * result + ((fechaAbono == null) ? 0 : fechaAbono.hashCode());
		result = prime * result + ((fechaCosecha == null) ? 0 : fechaCosecha.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((fechaPoda == null) ? 0 : fechaPoda.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((planta == null) ? 0 : planta.hashCode());
		result = prime * result + ((proximoRiego == null) ? 0 : proximoRiego.hashCode());
		result = prime * result + ((tarea == null) ? 0 : tarea.hashCode());
		result = prime * result + ((ultimoRiego == null) ? 0 : ultimoRiego.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguimiento other = (Seguimiento) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (etapa == null) {
			if (other.etapa != null)
				return false;
		} else if (!etapa.equals(other.etapa))
			return false;
		if (fechaAbono == null) {
			if (other.fechaAbono != null)
				return false;
		} else if (!fechaAbono.equals(other.fechaAbono))
			return false;
		if (fechaCosecha == null) {
			if (other.fechaCosecha != null)
				return false;
		} else if (!fechaCosecha.equals(other.fechaCosecha))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (fechaPoda == null) {
			if (other.fechaPoda != null)
				return false;
		} else if (!fechaPoda.equals(other.fechaPoda))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (planta == null) {
			if (other.planta != null)
				return false;
		} else if (!planta.equals(other.planta))
			return false;
		if (proximoRiego == null) {
			if (other.proximoRiego != null)
				return false;
		} else if (!proximoRiego.equals(other.proximoRiego))
			return false;
		if (tarea == null) {
			if (other.tarea != null)
				return false;
		} else if (!tarea.equals(other.tarea))
			return false;
		if (ultimoRiego == null) {
			if (other.ultimoRiego != null)
				return false;
		} else if (!ultimoRiego.equals(other.ultimoRiego))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seguimiento [id=" + id + ", planta=" + planta + ", estado=" + estado + ", etapa=" + etapa + ", tarea="
				+ tarea + ", ultimoRiego=" + ultimoRiego + ", proximoRiego=" + proximoRiego + ", fechaInicio="
				+ fechaInicio + ", fechaAbono=" + fechaAbono + ", fechaPoda=" + fechaPoda + ", fechaCosecha="
				+ fechaCosecha + "]";
	}

	public Seguimiento() {
		super();
	}
}
