package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.SeguimientoConvertor;
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@Service
public class SeguimientoManager {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);

	@Autowired
	private SeguimientoConvertor seguimientoConvertor;

	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	public void create(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.create(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public List<SeguimientoDTO> showAll() {
		try {
			return seguimientoConvertor.convertToListDTO(seguimientoServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public void regar(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.regar(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public void podar(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.podar(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public void cosechar(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.cosechar(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public void transplantar(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.transplantar(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public void sembrar(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.sembrado(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public void sueloPreparado(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.sueloPreparado(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public void abonar(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.abonar(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public List<SeguimientoDTO> findByUser(Usuario usuario) {
		return seguimientoConvertor.convertToListDTO(seguimientoServiceImpl.findByUser(usuario.getId()));
	}
	
	public List<SeguimientoDTO> findByPlanta(Planta planta) {
		return seguimientoConvertor.convertToListDTO(seguimientoServiceImpl.findByPlanta(planta.getId()));
	}

	public SeguimientoDTO findById(Integer id) {
		return seguimientoConvertor.convertToDTO(seguimientoServiceImpl.findById(id));
	}

	public void delete(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.remove(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

	public void update(SeguimientoDTO seguimientoDTO) {
		seguimientoServiceImpl.update(seguimientoConvertor.convertToEntity(seguimientoDTO));
	}

}
