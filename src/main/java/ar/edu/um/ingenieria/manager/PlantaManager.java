package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.PlantaConvertor;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@Service
public class PlantaManager {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);

	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	@Autowired
	private PlantaConvertor plantaConvertor;

	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	public void create(PlantaDTO plantaDTO) {
		Planta planta = plantaConvertor.convertToEntity(plantaDTO);
		plantaServiceImpl.create(planta);
	}

	public List<PlantaDTO> showAll() {
		try {
			return plantaConvertor.convertToListDTO(plantaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public PlantaDTO findById(Integer id) {
		return plantaConvertor.convertToDTO(plantaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Planta planta = plantaServiceImpl.findById(id);
		for (int i = 0; i < planta.getSeguimiento().size(); i++) {
			seguimientoServiceImpl.remove(planta.getSeguimiento().get(i));
		}
		plantaServiceImpl.remove(planta);
	}

}
