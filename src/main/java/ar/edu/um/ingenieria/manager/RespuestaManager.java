package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.RespuestaConvertor;
import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.dto.RespuestaDTO;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;

@Service
public class RespuestaManager {

	private static final Logger logger = LoggerFactory.getLogger(CategoriaManager.class);

	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;

	@Autowired
	private RespuestaConvertor respuestaConvertor;

	public void create(RespuestaDTO respuestaDTO) {
		Respuesta respuesta = respuestaConvertor.convertToEntity(respuestaDTO);
		respuestaServiceImpl.create(respuesta);
	}

	public List<RespuestaDTO> showAll() {
		try {
			return respuestaConvertor.convertToListDTO(respuestaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public RespuestaDTO findById(Integer id) {
		return respuestaConvertor.convertToDTO(respuestaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Respuesta respuesta = respuestaServiceImpl.findById(id);
		respuestaServiceImpl.remove(respuesta);
	}

}
