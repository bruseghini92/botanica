package ar.edu.um.ingenieria.dto;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PreguntaDTO {
	
	private Integer id;
	
	@NotNull
	@Size(min=2,max=45)
	private String titulo;
	@NotNull	
	private UsuarioDTO usuario;
	@NotNull
	private Boolean cerrado;
	@NotNull	
	@Size(min=2,max=255)
	private String texto;
	@NotNull	
	private TemaDTO tema;
	
	private Date fecha;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public Boolean getCerrado() {
		return cerrado;
	}
	public void setCerrado(Boolean cerrado) {
		this.cerrado = cerrado;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public TemaDTO getTema() {
		return tema;
	}
	public void setTema(TemaDTO tema) {
		this.tema = tema;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cerrado == null) ? 0 : cerrado.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tema == null) ? 0 : tema.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		PreguntaDTO other = (PreguntaDTO) obj;
		if (cerrado == null) {
			if (other.cerrado != null)
				return false;
		} else if (!cerrado.equals(other.cerrado))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tema == null) {
			if (other.tema != null)
				return false;
		} else if (!tema.equals(other.tema))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
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
		return "Pregunta [id=" + id + ", titulo=" + titulo + ", usuario=" + usuario + ", cerrado=" + cerrado
				+ ", texto=" + texto + ", tema=" + tema + ", fecha=" + fecha + "]";
	}
	public PreguntaDTO() {
		super();
	}	
}
