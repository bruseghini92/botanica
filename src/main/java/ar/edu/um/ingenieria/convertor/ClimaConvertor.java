package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Clima;
import ar.edu.um.ingenieria.dto.ClimaDTO;

@Service
public class ClimaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public ClimaDTO convertToDTO(Clima clima) {
		try {
			ClimaDTO cdto = mapper.map(clima, ClimaDTO.class);
			return cdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Clima convertToEntity (ClimaDTO cdto) {
		try {
			Clima clima = mapper.map(cdto, Clima.class);
			return clima;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<ClimaDTO> convertToListDTO(List<Clima> listClima){
		List<ClimaDTO> listClimaDTO = new ArrayList<ClimaDTO>();
		ClimaDTO climaDTO = null;
		try {
			for (Clima clima : listClima) {
				climaDTO = mapper.map(clima, ClimaDTO.class);
				listClimaDTO.add(climaDTO);
			}
			return listClimaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
