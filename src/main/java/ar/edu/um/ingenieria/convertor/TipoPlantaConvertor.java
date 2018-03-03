package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.TipoPlanta;
import ar.edu.um.ingenieria.dto.TipoPlantaDTO;

@Service
public class TipoPlantaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public TipoPlantaDTO convertToDTO(TipoPlanta tipoPlanta) {
		try {
			TipoPlantaDTO tdto = mapper.map(tipoPlanta, TipoPlantaDTO.class);
			return tdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public TipoPlanta convertToEntity (TipoPlantaDTO tdto) {
		try {
			TipoPlanta tipoPlanta = mapper.map(tdto, TipoPlanta.class);
			return tipoPlanta;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<TipoPlantaDTO> convertToListDTO(List<TipoPlanta> listTipoPlanta){
		List<TipoPlantaDTO> listTipoPlantaDTO = new ArrayList<TipoPlantaDTO>();
		TipoPlantaDTO tipoPlantaDTO = null;
		try {
			for (TipoPlanta tipoPlanta : listTipoPlanta) {
				tipoPlantaDTO = mapper.map(tipoPlanta, TipoPlantaDTO.class);
				listTipoPlantaDTO.add(tipoPlantaDTO);
			}
			return listTipoPlantaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
