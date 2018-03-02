package ar.edu.um.ingenieria.dto;

import java.sql.Date;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class RespuestaDTO {
	
	private Integer id;
	
	private TemaDTO temaDTO;
	
	@NotNull
	@Size(min=2,max=255)
	private String texto;

	private UsuarioDTO usuarioDTO;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	@Column(name = "fecha")
	private Date fecha;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TemaDTO getTemaDTO() {
		return temaDTO;
	}

	public void setTemaDTO(TemaDTO temaDTO) {
		this.temaDTO = temaDTO;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "RespuestaDTO [id=" + id + ", temaDTO=" + temaDTO + ", texto=" + texto + ", usuarioDTO=" + usuarioDTO
				+ ", fecha=" + fecha + "]";
	}

	public RespuestaDTO() {
		super();
	}	
}
