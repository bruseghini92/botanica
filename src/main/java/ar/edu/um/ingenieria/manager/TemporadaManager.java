package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.TemporadaConvertor;
import ar.edu.um.ingenieria.domain.Temporada;
import ar.edu.um.ingenieria.dto.TemporadaDTO;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;

@Service
public class TemporadaManager {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);

	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	@Autowired
	private TemporadaConvertor temporadaConvertor;

	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;

	public void create(TemporadaDTO temporadaDTO) {
		Temporada temporada = temporadaConvertor.convertToEntity(temporadaDTO);
		temporadaServiceImpl.create(temporada);
	}

	public List<TemporadaDTO> showAll() {
		try {
			return temporadaConvertor.convertToListDTO(temporadaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public void update(TemporadaDTO temporadaDTO) {
		temporadaServiceImpl.update(temporadaConvertor.convertToEntity(temporadaDTO));
	}

	public TemporadaDTO findById(int id) {
		return temporadaConvertor.convertToDTO(temporadaServiceImpl.findById(id));
	}

	public void delete(TemporadaDTO temporadaDTO) {
		Temporada temporada = temporadaConvertor.convertToEntity(temporadaDTO);
		for (int i = 0; i < temporada.getPlantas().size(); i++) {
			plantaServiceImpl.remove(temporada.getPlantas().get(i));
		}
		temporadaServiceImpl.remove(temporada);
	}

}
