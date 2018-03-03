package ar.edu.um.ingenieria.manager;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.EstadoConvertor;
import ar.edu.um.ingenieria.convertor.TipoVentaConvertor;
import ar.edu.um.ingenieria.convertor.UsuarioConvertor;
import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.dto.TipoVentaDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@Service
public class VentaManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	
	@Autowired
	private EstadoConvertor estadoConvertor;
	
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	
	@Autowired
	private TipoVentaConvertor tipoVentaConvertor;
	
	@Autowired
	private UsuarioConvertor usuarioConvertor;
	
	public void create (String producto,String descripcion,TipoVentaDTO tipoVentaDTO,UsuarioDTO usuarioDTO,Boolean cerrado,Date fecha) {
		Venta venta = new Venta();
		venta.setProducto(producto);
		venta.setDescripcion(descripcion);
		venta.setTipoVenta(tipoVentaConvertor.convertToEntity(tipoVentaDTO));
		venta.setUsuario(usuarioConvertor.convertToEntity(usuarioDTO));
		venta.setCerrado(cerrado);
		venta.setFecha(fecha);
		ventaServiceImpl.create(venta);
	}
	
	public List<EstadoDTO> showAll(){
		try {
			return estadoConvertor.convertToListDTO(estadoServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public EstadoDTO findById(Integer id) {
		return estadoConvertor.convertToDTO(estadoServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Estado estado = estadoServiceImpl.findById(id);
		for (int i = 0; i < estado.getSeguimientos().size();i++) {
		seguimientoServiceImpl.remove(estado.getSeguimientos().get(i));
		}
		estadoServiceImpl.remove(estado);
	}

}
