package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.dto.RespuestaDTO;

@Service
public class RespuestaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoConvertor.class);
		
	public RespuestaDTO convertToDTO(Respuesta respuesta) {
		try {
			RespuestaDTO rdto = mapper.map(respuesta, RespuestaDTO.class);
			return rdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Respuesta convertToEntity (RespuestaDTO rdto) {
		try {
			Respuesta respuesta = mapper.map(rdto, Respuesta.class);
			return respuesta;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<RespuestaDTO> convertToListDTO(List<Respuesta> listRespuesta){
		List<RespuestaDTO> listRespuestaDTO = new ArrayList<RespuestaDTO>();
		RespuestaDTO respuestaDTO = null;
		try {
			for (Respuesta respuesta : listRespuesta) {
				respuestaDTO = mapper.map(respuesta, RespuestaDTO.class);
				listRespuestaDTO.add(respuestaDTO);
			}
			return listRespuestaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
