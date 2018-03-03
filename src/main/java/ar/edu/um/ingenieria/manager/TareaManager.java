package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.TareaConvertor;
import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.dto.TareaDTO;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.TareaServiceImpl;

@Service
public class TareaManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	
	@Autowired
	private TareaConvertor tareaConvertor;
	
	@Autowired
	private TareaServiceImpl tareaServiceImpl;
	
	public void create (TareaDTO tareaDTO) {
		Tarea tarea = tareaConvertor.convertToEntity(tareaDTO);
		tareaServiceImpl.create(tarea);
	}
	
	public List<TareaDTO> showAll(){
		try {
			return tareaConvertor.convertToListDTO(tareaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public TareaDTO findById(Integer id) {
		return tareaConvertor.convertToDTO(tareaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Tarea tarea = tareaServiceImpl.findById(id);
		for (int i = 0; i < tarea.getSeguimiento().size();i++) {
		seguimientoServiceImpl.remove(tarea.getSeguimiento().get(i));
		}
		tareaServiceImpl.remove(tarea);
	}

}
