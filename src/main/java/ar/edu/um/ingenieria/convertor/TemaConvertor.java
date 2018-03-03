package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.dto.TemaDTO;

@Service
public class TemaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public TemaDTO convertToDTO(Tema tema) {
		try {
			TemaDTO tdto = mapper.map(tema, TemaDTO.class);
			return tdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Tema convertToEntity (TemaDTO tdto) {
		try {
			Tema tema = mapper.map(tdto, Tema.class);
			return tema;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<TemaDTO> convertToListDTO(List<Tema> listTema){
		List<TemaDTO> listTemaDTO = new ArrayList<TemaDTO>();
		TemaDTO temaDTO = null;
		try {
			for (Tema tema : listTema) {
				temaDTO = mapper.map(tema, TemaDTO.class);
				listTemaDTO.add(temaDTO);
			}
			return listTemaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
