package ar.edu.um.ingenieria.convertor;


import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Rol;
import ar.edu.um.ingenieria.dto.RolDTO;

@Service
public class RolConvertor {
	@Autowired
	private Mapper mapper;
	private static final Logger logger = LoggerFactory.getLogger(RolConvertor.class);
		
	public RolDTO convertToDTO(Rol rol) {
		try {
			RolDTO rolDTO = mapper.map(rol, RolDTO.class);
			return rolDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Rol convertToEntity (RolDTO rolDTO) {
		try {
			Rol rol = mapper.map(rolDTO, Rol.class);
			return rol;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
