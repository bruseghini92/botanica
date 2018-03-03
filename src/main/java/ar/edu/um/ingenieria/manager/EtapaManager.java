package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.EtapaConvertor;
import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.dto.EtapaDTO;
import ar.edu.um.ingenieria.service.impl.EtapaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TareaServiceImpl;

@Service
public class EtapaManager {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private TareaServiceImpl tareaServiceImpl;
	
	@Autowired
	private EtapaConvertor etapaConvertor;
	
	@Autowired
	private EtapaServiceImpl etapaServiceImpl;
	
	public void create (EtapaDTO etapaDTO) {
		Etapa etapa = etapaConvertor.convertToEntity(etapaDTO);
		etapaServiceImpl.create(etapa);
	}
	
	public List<EtapaDTO> showAll(){
		try {
			return etapaConvertor.convertToListDTO(etapaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public EtapaDTO findById(Integer id) {
		return etapaConvertor.convertToDTO(etapaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Etapa etapa = etapaServiceImpl.findById(id);
		for (int i = 0; i < etapa.getTareas().size();i++) {
		tareaServiceImpl.remove(etapa.getTareas().get(i));
		}
		etapaServiceImpl.remove(etapa);
	}

}
