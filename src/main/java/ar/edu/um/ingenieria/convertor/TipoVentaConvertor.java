package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.dto.TipoVentaDTO;

@Service
public class TipoVentaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public TipoVentaDTO convertToDTO(TipoVenta tipoVenta) {
		try {
			TipoVentaDTO tdto = mapper.map(tipoVenta, TipoVentaDTO.class);
			return tdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public TipoVenta convertToEntity (TipoVentaDTO tdto) {
		try {
			TipoVenta tipoVenta = mapper.map(tdto, TipoVenta.class);
			return tipoVenta;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<TipoVentaDTO> convertToListDTO(List<TipoVenta> listTipoVenta){
		List<TipoVentaDTO> listTipoVentaDTO = new ArrayList<TipoVentaDTO>();
		TipoVentaDTO tipoVentaDTO = null;
		try {
			for (TipoVenta tipoVenta : listTipoVenta) {
				tipoVentaDTO = mapper.map(tipoVenta, TipoVentaDTO.class);
				listTipoVentaDTO.add(tipoVentaDTO);
			}
			return listTipoVentaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}
}
