package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.TipoVentaConvertor;
import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.dto.TipoVentaDTO;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@Service
public class TipoVentaManager {
private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	
	@Autowired
	private TipoVentaConvertor tipoVentaConvertor;
	
	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;
	
	public void create (TipoVentaDTO tipoVentaDTO) {
		TipoVenta tipoVenta = tipoVentaConvertor.convertToEntity(tipoVentaDTO);
		tipoVentaServiceImpl.create(tipoVenta);
	}
	
	public List<TipoVentaDTO> showAll(){
		try {
			return tipoVentaConvertor.convertToListDTO(tipoVentaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public TipoVentaDTO findById(Integer id) {
		return tipoVentaConvertor.convertToDTO(tipoVentaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		TipoVenta tipoVenta = tipoVentaServiceImpl.findById(id);
		for (int i = 0; i < tipoVenta.getVentas().size();i++) {
		ventaServiceImpl.remove(tipoVenta.getVentas().get(i));
		}
		tipoVentaServiceImpl.remove(tipoVenta);
	}

}
