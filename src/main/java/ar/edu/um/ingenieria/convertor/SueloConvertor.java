package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Suelo;
import ar.edu.um.ingenieria.dto.SueloDTO;

@Service
public class SueloConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public SueloDTO convertToDTO(Suelo suelo) {
		try {
			SueloDTO sdto = mapper.map(suelo, SueloDTO.class);
			return sdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Suelo convertToEntity (SueloDTO sdto) {
		try {
			Suelo suelo = mapper.map(sdto, Suelo.class);
			return suelo;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<SueloDTO> convertToListDTO(List<Suelo> listSuelo){
		List<SueloDTO> listSueloDTO = new ArrayList<SueloDTO>();
		SueloDTO sueloDTO = null;
		try {
			for (Suelo suelo : listSuelo) {
				sueloDTO = mapper.map(suelo, SueloDTO.class);
				listSueloDTO.add(sueloDTO);
			}
			return listSueloDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
