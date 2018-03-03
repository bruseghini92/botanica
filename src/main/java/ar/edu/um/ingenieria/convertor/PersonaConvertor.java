package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.dto.PersonaDTO;

@Service
public class PersonaConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoConvertor.class);
		
	public PersonaDTO convertToDTO(Persona persona) {
		try {
			PersonaDTO pdto = mapper.map(persona, PersonaDTO.class);
			return pdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Persona convertToEntity (PersonaDTO pdto) {
		try {
			Persona persona = mapper.map(pdto, Persona.class);
			return persona;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<PersonaDTO> convertToListDTO(List<Persona> listPersona){
		List<PersonaDTO> listPersonaDTO = new ArrayList<PersonaDTO>();
		PersonaDTO personaDTO = null;
		try {
			for (Persona persona : listPersona) {
				personaDTO = mapper.map(persona, PersonaDTO.class);
				listPersonaDTO.add(personaDTO);
			}
			return listPersonaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}
}
