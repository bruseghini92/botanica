package ar.edu.um.ingenieria.convertor;


import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.UsuarioDTO;

@Service
public class UsuarioConvertor {
	@Autowired
	private Mapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioConvertor.class);
		
	public UsuarioDTO convertToDTO(Usuario usuario) {
		try {
			UsuarioDTO udto = mapper.map(usuario, UsuarioDTO.class);
			return udto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Usuario convertToEntity (UsuarioDTO udto) {
		try {
			Usuario usuario = mapper.map(udto, Usuario.class);
			return usuario;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<UsuarioDTO> convertToListDTO(List<Usuario> listUsuario){
		List<UsuarioDTO> listUsuarioDTO = new ArrayList<UsuarioDTO>();
		UsuarioDTO usuarioDTO = null;
		try {
			for (Usuario usuario : listUsuario) {
				usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
				listUsuarioDTO.add(usuarioDTO);
			}
			return listUsuarioDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;		
	}

}
