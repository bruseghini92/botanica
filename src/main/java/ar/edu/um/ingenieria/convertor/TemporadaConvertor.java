package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Temporada;
import ar.edu.um.ingenieria.dto.TemporadaDTO;

@Service
public class TemporadaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public TemporadaDTO convertToDTO(Temporada temporada) {
		try {
			TemporadaDTO tdto = mapper.map(temporada, TemporadaDTO.class);
			return tdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Temporada convertToEntity (TemporadaDTO tdto) {
		try {
			Temporada temporada = mapper.map(tdto, Temporada.class);
			return temporada;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<TemporadaDTO> convertToListDTO(List<Temporada> listTemporada){
		List<TemporadaDTO> listTemaDTO = new ArrayList<TemporadaDTO>();
		TemporadaDTO temporadaDTO = null;
		try {
			for (Temporada temporada : listTemporada) {
				temporadaDTO = mapper.map(temporada, TemporadaDTO.class);
				listTemaDTO.add(temporadaDTO);
			}
			return listTemaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
