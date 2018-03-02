package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.UsuarioConvertor;
import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.service.impl.PersonaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;
@Service
public class UsuarioManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private UsuarioConvertor usuarioConvertor;
	
	@Autowired
	private PersonaServiceImpl personaServiceImpl;
	
	public void create(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioConvertor.convertToEntity(usuarioDTO);
		Persona persona = usuario.getPersona();
		usuario.setPersona(null);
		usuarioServiceImpl.create(usuario);
		persona.setUsuario(usuario);
		personaServiceImpl.create(persona);	
	}
	
	public List<UsuarioDTO> showAll(){
		try {
			return usuarioConvertor.convertToListDTO(usuarioServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public UsuarioDTO findById(Integer id) {
		return usuarioConvertor.convertToDTO(usuarioServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(id);
		personaServiceImpl.remove(usuario.getPersona());		
		usuarioServiceImpl.remove(usuario);
	}
}
