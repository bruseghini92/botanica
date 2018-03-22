package ar.edu.um.ingenieria.convertor;

import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.dto.CategoriaDTO;

@Service
public class CategoriaConvertor {
	@Autowired
	private Mapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoConvertor.class);

	public CategoriaDTO convertToDTO(Categoria categoria) {
		try {
			CategoriaDTO cdto = mapper.map(categoria, CategoriaDTO.class);
			return cdto;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public Categoria convertToEntity(CategoriaDTO cdto) {
		try {
			Categoria categoria = mapper.map(cdto, Categoria.class);
			return categoria;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public List<CategoriaDTO> convertToListDTO(List<Categoria> listCategoria) {
		List<CategoriaDTO> listCategoriaDTO = new ArrayList<CategoriaDTO>();
		CategoriaDTO categoriaDTO = null;
		try {
			for (Categoria categoria : listCategoria) {
				categoriaDTO = mapper.map(categoria, CategoriaDTO.class);
				listCategoriaDTO.add(categoriaDTO);
			}
			return listCategoriaDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
