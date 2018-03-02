package ar.edu.um.ingenieria.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import ar.edu.um.ingenieria.domain.TipoVenta;

public class VentaDTO {
	private Integer id;
	
	@NotNull
	@Size(min=2,max=45)
	private String producto;
	
	@NotNull
	@Size(min=2,max=255)
	private String descripcion;
	
	private TipoVenta tipo_venta_id;
	
	private UsuarioDTO usuario;
	
	private Boolean cerrado;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date fecha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoVenta getTipo_venta_id() {
		return tipo_venta_id;
	}

	public void setTipo_venta_id(TipoVenta tipo_venta_id) {
		this.tipo_venta_id = tipo_venta_id;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "VentaDTO [id=" + id + ", producto=" + producto + ", descripcion=" + descripcion + ", tipo_venta_id="
				+ tipo_venta_id + ", usuario=" + usuario + ", cerrado=" + cerrado + ", fecha=" + fecha + "]";
	}
}
