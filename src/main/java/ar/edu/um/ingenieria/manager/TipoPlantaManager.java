package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.TipoPlantaConvertor;
import ar.edu.um.ingenieria.domain.TipoPlanta;
import ar.edu.um.ingenieria.dto.TipoPlantaDTO;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@Service
public class TipoPlantaManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired
	private TipoPlantaConvertor tipoPlantaConvertor;
	
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;
	
	public void create (TipoPlantaDTO tipoPlantaDTO) {
		TipoPlanta tipoPlanta = tipoPlantaConvertor.convertToEntity(tipoPlantaDTO);
		tipoPlantaServiceImpl.create(tipoPlanta);
	}
	
	public List<TipoPlantaDTO> showAll(){
		try {
			return tipoPlantaConvertor.convertToListDTO(tipoPlantaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public TipoPlantaDTO findById(Integer id) {
		return tipoPlantaConvertor.convertToDTO(tipoPlantaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		TipoPlanta tipoPlanta = tipoPlantaServiceImpl.findById(id);
		for (int i = 0; i < tipoPlanta.getPlantas().size();i++) {
		plantaServiceImpl.remove(tipoPlanta.getPlantas().get(i));
		}
		tipoPlantaServiceImpl.remove(tipoPlanta);
	}

}
