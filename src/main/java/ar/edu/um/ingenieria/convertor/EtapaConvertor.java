package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.dto.EtapaDTO;

@Service
public class EtapaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);
		
	public EtapaDTO convertToDTO(Etapa etapa) {
		try {
			EtapaDTO edto = mapper.map(etapa, EtapaDTO.class);
			return edto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Etapa convertToEntity (EtapaDTO edto) {
		try {
			Etapa etapa = mapper.map(edto, Etapa.class);
			return etapa;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<EtapaDTO> convertToListDTO(List<Etapa> listEtapa){
		List<EtapaDTO> listEtapaDTO = new ArrayList<EtapaDTO>();
		EtapaDTO etapaDTO = null;
		try {
			for (Etapa etapa : listEtapa) {
				etapaDTO = mapper.map(etapa, EtapaDTO.class);
				listEtapaDTO.add(etapaDTO);
			}
			return listEtapaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}
}
