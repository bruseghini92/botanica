package ar.edu.um.ingenieria.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class TemaDTO {	
	
	private Integer id;
	
	@NotNull
	@Size(min=2,max=45)
	private String titulo;
	
	private UsuarioDTO usuarioDTO;

	private Boolean cerrado;
	
	@NotNull
	@Size(min=2,max=255)
	private String texto;
	
	private CategoriaDTO categoriaDTO;
	
	private List<RespuestaDTO> respuestasDTO;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
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
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
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
	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}
	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}
	public List<RespuestaDTO> getRespuestasDTO() {
		return respuestasDTO;
	}
	public void setRespuestasDTO(List<RespuestaDTO> respuestasDTO) {
		this.respuestasDTO = respuestasDTO;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "TemaDTO [id=" + id + ", titulo=" + titulo + ", usuarioDTO=" + usuarioDTO + ", cerrado=" + cerrado
				+ ", texto=" + texto + ", categoriaDTO=" + categoriaDTO + ", respuestasDTO=" + respuestasDTO
				+ ", fecha=" + fecha + "]";
	}
	public TemaDTO() {
		super();
	}
}
