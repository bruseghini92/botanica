package ar.edu.um.ingenieria.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.CategoriaConvertor;
import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.dto.CategoriaDTO;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;


@Service
public class CategoriaManager {
	
private static final Logger logger = LoggerFactory.getLogger(CategoriaManager.class);
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@Autowired
	private CategoriaConvertor categoriaConvertor;
	
	public void create(CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaConvertor.convertToEntity(categoriaDTO);
		categoriaServiceImpl.create(categoria);
	}
	
	public List<CategoriaDTO> showAll(){
		try {
			return categoriaConvertor.convertToListDTO(categoriaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public CategoriaDTO findById(Integer id) {
		return categoriaConvertor.convertToDTO(categoriaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Categoria categoria = categoriaServiceImpl.findById(id);	
		categoriaServiceImpl.remove(categoria);
	}

}
