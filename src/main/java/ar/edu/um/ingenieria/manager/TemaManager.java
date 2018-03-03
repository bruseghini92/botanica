package ar.edu.um.ingenieria.manager;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.CategoriaConvertor;
import ar.edu.um.ingenieria.convertor.TemaConvertor;
import ar.edu.um.ingenieria.convertor.UsuarioConvertor;
import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.dto.CategoriaDTO;
import ar.edu.um.ingenieria.dto.TemaDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Service
public class TemaManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private UsuarioConvertor usuarioConvertor;
	
	@Autowired
	private CategoriaConvertor categoriaConvertor;
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	
	@Autowired
	private TemaServiceImpl temaServiceImpl;
	
	@Autowired
	private TemaConvertor temaConvertor;
	
	public void create (UsuarioDTO usuarioDTO,String titulo, Boolean cerrado,String texto,List<Respuesta> respuestas, CategoriaDTO categoriaDTO, Date fecha) {
		Tema tema = new Tema();
		tema.setUsuario(usuarioConvertor.convertToEntity(usuarioDTO));
		tema.setCerrado(cerrado);
		tema.setFecha(fecha);
		tema.setRespuestas(respuestas);
		tema.setTexto(texto);
		tema.setTitulo(titulo);
		tema.setCategoria(categoriaConvertor.convertToEntity(categoriaDTO));
		temaServiceImpl.create(tema);
	}
	
	public List<TemaDTO> showAll(){
		try {
			return temaConvertor.convertToListDTO(temaServiceImpl.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public TemaDTO findById(Integer id) {
		return temaConvertor.convertToDTO(temaServiceImpl.findById(id));
	}

	public void delete(Integer id) {
		Tema tema = temaServiceImpl.findById(id);
		categoriaServiceImpl.remove(tema.getCategoria());	
		usuarioServiceImpl.remove(tema.getUsuario());
		for (int i = 0; i < tema.getRespuestas().size();i++) {
		respuestaServiceImpl.remove(tema.getRespuestas().get(i));
		}
		temaServiceImpl.remove(tema);
	}

}
