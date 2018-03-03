package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.dto.VentaDTO;

@Service
public class VentaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public VentaDTO convertToDTO(Venta venta) {
		try {
			VentaDTO vdto = mapper.map(venta, VentaDTO.class);
			return vdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Venta convertToEntity (VentaDTO vdto) {
		try {
			Venta venta = mapper.map(vdto, Venta.class);
			return venta;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<VentaDTO> convertToListDTO(List<Venta> listVenta){
		List<VentaDTO> listVentaDTO = new ArrayList<VentaDTO>();
		VentaDTO ventaDTO = null;
		try {
			for (Venta venta : listVenta) {
				ventaDTO = mapper.map(venta, VentaDTO.class);
				listVentaDTO.add(ventaDTO);
			}
			return listVentaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
