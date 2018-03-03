package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.dto.EstadoDTO;

@Service
public class EstadoConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoConvertor.class);
		
	public EstadoDTO convertToDTO(Estado estado) {
		try {
			EstadoDTO edto = mapper.map(estado, EstadoDTO.class);
			return edto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Estado convertToEntity (EstadoDTO edto) {
		try {
			Estado estado = mapper.map(edto, Estado.class);
			return estado;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<EstadoDTO> convertToListDTO(List<Estado> listEstado){
		List<EstadoDTO> listEstadoDTO = new ArrayList<EstadoDTO>();
		EstadoDTO estadoDTO = null;
		try {
			for (Estado estado : listEstado) {
				estadoDTO = mapper.map(estado, EstadoDTO.class);
				listEstadoDTO.add(estadoDTO);
			}
			return listEstadoDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
