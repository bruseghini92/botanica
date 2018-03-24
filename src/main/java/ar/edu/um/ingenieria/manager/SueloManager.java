package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.SueloConvertor;
import ar.edu.um.ingenieria.domain.Suelo;
import ar.edu.um.ingenieria.dto.SueloDTO;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;

@Service
public class SueloManager {
private static final Logger logger = LoggerFactory.getLogger(ClimaManager.class);
	
	@Autowired
	private SueloServiceImpl sueloServiceImpl;
	
	@Autowired
	private SueloConvertor sueloConvertor;
	
	public void create(SueloDTO sueloDTO) {
		Suelo suelo = sueloConvertor.convertToEntity(sueloDTO);
		sueloServiceImpl.create(suelo);
	}
	
	public List<SueloDTO> showAll(){
		try {
			return sueloConvertor.convertToListDTO(sueloServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public SueloDTO findById(int id) {
		return sueloConvertor.convertToDTO(sueloServiceImpl.findById(id));
	}

	public void delete(SueloDTO sueloDTO) {
		Suelo suelo = sueloConvertor.convertToEntity(sueloDTO);	
		sueloServiceImpl.remove(suelo);
	}

}
