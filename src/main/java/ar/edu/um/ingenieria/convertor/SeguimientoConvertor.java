package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;

@Service
public class SeguimientoConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public SeguimientoDTO convertToDTO(Seguimiento seguimiento) {
		try {
			SeguimientoDTO sdto = mapper.map(seguimiento, SeguimientoDTO.class);
			return sdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Seguimiento convertToEntity (SeguimientoDTO sdto) {
		try {
			Seguimiento seguimiento = mapper.map(sdto, Seguimiento.class);
			return seguimiento;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<SeguimientoDTO> convertToListDTO(List<Seguimiento> listSeguimiento){
		List<SeguimientoDTO> listSeguimientoDTO = new ArrayList<SeguimientoDTO>();
		SeguimientoDTO seguimientoDTO = null;
		try {
			for (Seguimiento seguimiento : listSeguimiento) {
				seguimientoDTO = mapper.map(seguimiento, SeguimientoDTO.class);
				listSeguimientoDTO.add(seguimientoDTO);
			}
			return listSeguimientoDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}
}
