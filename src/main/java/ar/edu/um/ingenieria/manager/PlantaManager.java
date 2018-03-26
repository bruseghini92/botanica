package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.PlantaConvertor;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;

@Service
public class PlantaManager {

	private static final Logger logger = LoggerFactory.getLogger(PlantaManager.class);

	@Autowired
	private PlantaConvertor plantaConvertor;

	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	@Autowired
	private SeguimientoManager seguimientoManager;

	public void create(PlantaDTO plantaDTO) {
		logger.info("Planta MANAGER create");
		plantaServiceImpl.create(plantaConvertor.convertToEntity(plantaDTO));
	}

	public List<PlantaDTO> showAll() {
		logger.info("Planta MANAGER showAll");
		try {
			return plantaConvertor.convertToListDTO(plantaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public PlantaDTO findById(Integer id) {
		logger.info("Planta MANAGER findById");
		return plantaConvertor.convertToDTO(plantaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		logger.info("Planta MANAGER delete");
		int i = 0;
		Planta planta = plantaConvertor.convertToEntity(this.findById(id));
		List<SeguimientoDTO> seguimiento = seguimientoManager.findByPlanta(planta);
		while (i < seguimiento.size()) {
			seguimientoManager.delete(seguimiento.get(i));
			i++;
		}
		plantaServiceImpl.remove(planta);
	}

	public void update(PlantaDTO plantaDTO) {
		logger.info("Planta MANAGER update");
		plantaServiceImpl.update(plantaConvertor.convertToEntity(plantaDTO));
	}

}
