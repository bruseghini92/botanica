package ar.edu.um.ingenieria.controller.foro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/foro/respuesta")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class RespuestaController {
	
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;
	@Autowired
	private TemaServiceImpl temaServiceImpl;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping("/leer/{id}")
	public ResponseEntity<List<Respuesta>> findByTema(@PathVariable Integer id){
		if (temaServiceImpl.findById(id) == null) {
			return new ResponseEntity<List<Respuesta>>(HttpStatus.CONFLICT);
		}else {
			Tema tema = temaServiceImpl.findById(id);
			if(tema.getRespuestas() == null) {
				new ResponseEntity<List<Respuesta>>(HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<Respuesta>>(tema.getRespuestas(), HttpStatus.OK);
		}		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Respuesta> findById(@PathVariable Integer id){
		if (respuestaServiceImpl.findById(id) == null) {
			return new ResponseEntity<Respuesta>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Respuesta>(respuestaServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(String texto, Integer idTema, Integer idUsuario, String fecha) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Respuesta respuesta = new Respuesta();
		respuesta.setTexto(texto);
		respuesta.setFecha(simpleDateFormat.parse(fecha));
		respuesta.setUsuario(usuarioServiceImpl.findById(idUsuario));
		respuesta.setTema(temaServiceImpl.findById(idTema));
		respuestaServiceImpl.create(respuesta);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	@PostMapping("/edit/")
	public ResponseEntity<Void> edit(Integer id, String texto, Integer idTema, Integer idUsuario, String fecha) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		if (respuestaServiceImpl.findById(id) == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			Respuesta respuesta = respuestaServiceImpl.findById(id);
			respuesta.setTexto(texto);
			respuesta.setFecha(simpleDateFormat.parse(fecha));
			respuesta.setUsuario(usuarioServiceImpl.findById(idUsuario));
			respuesta.setTema(temaServiceImpl.findById(idTema));
			respuestaServiceImpl.update(respuesta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		if (respuestaServiceImpl.findById(id) == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		respuestaServiceImpl.remove(respuestaServiceImpl.findById(id));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
