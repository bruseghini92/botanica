package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.ClimaConvertor;
import ar.edu.um.ingenieria.domain.Clima;
import ar.edu.um.ingenieria.dto.ClimaDTO;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;

@Service
public class ClimaManager {
	
private static final Logger logger = LoggerFactory.getLogger(ClimaManager.class);
	
	@Autowired
	private ClimaServiceImpl climaServiceImpl;
	
	@Autowired
	private ClimaConvertor climaConvertor;
	
	public void create(ClimaDTO climaDTO) {
		Clima clima = climaConvertor.convertToEntity(climaDTO);
		climaServiceImpl.create(clima);
	}
	
	public List<ClimaDTO> showAll(){
		try {
			return climaConvertor.convertToListDTO(climaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public ClimaDTO findById(int id) {
		return climaConvertor.convertToDTO(climaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Clima clima = climaServiceImpl.findById(id);	
		climaServiceImpl.remove(clima);
	}

}
