package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.dto.PlantaDTO;

@Service
public class PlantaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoConvertor.class);
		
	public PlantaDTO convertToDTO(Planta planta) {
		try {
			PlantaDTO pdto = mapper.map(planta, PlantaDTO.class);
			return pdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Planta convertToEntity (PlantaDTO pdto) {
		try {
			Planta planta = mapper.map(pdto, Planta.class);
			return planta;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<PlantaDTO> convertToListDTO(List<Planta> listPlanta){
		List<PlantaDTO> listPlantaDTO = new ArrayList<PlantaDTO>();
		PlantaDTO plantaDTO = null;
		try {
			for (Planta planta : listPlanta) {
				plantaDTO = mapper.map(planta, PlantaDTO.class);
				listPlantaDTO.add(plantaDTO);
			}
			return listPlantaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}
}
