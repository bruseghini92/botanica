package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.dto.TareaDTO;

@Service
public class TareaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public TareaDTO convertToDTO(Tarea tarea) {
		try {
			TareaDTO udto = mapper.map(tarea, TareaDTO.class);
			return udto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Tarea convertToEntity (TareaDTO tdto) {
		try {
			Tarea tarea = mapper.map(tdto, Tarea.class);
			return tarea;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<TareaDTO> convertToListDTO(List<Tarea> listTarea){
		List<TareaDTO> listTareaDTO = new ArrayList<TareaDTO>();
		TareaDTO tareaDTO = null;
		try {
			for (Tarea tarea : listTarea) {
				tareaDTO = mapper.map(tarea, TareaDTO.class);
				listTareaDTO.add(tareaDTO);
			}
			return listTareaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}
}
