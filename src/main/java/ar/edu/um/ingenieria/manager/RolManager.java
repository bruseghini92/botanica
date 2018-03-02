package ar.edu.um.ingenieria.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.RolConvertor;
import ar.edu.um.ingenieria.dto.RolDTO;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;
@Service
public class RolManager {
	
	@Autowired
	private RolServiceImpl rolServiceImpl;
	
	@Autowired
	private RolConvertor rolConvertor;
	
	
	public RolDTO findById(int parseInt) {
		return rolConvertor.convertToDTO(rolServiceImpl.findById(parseInt));
	}

}
