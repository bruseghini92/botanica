package ar.edu.um.ingenieria.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/admin/tema")
@Secured({"ROLE_ADMIN"})
public class TemaAdmController {

	@Autowired
	private TemaServiceImpl temaServiceImpl;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@GetMapping("/leer/{id}")
	public ResponseEntity<List<Tema>> findByCategoria(@PathVariable Integer id){
		if (categoriaServiceImpl.findById(id) == null) {
			return new ResponseEntity<List<Tema>>(HttpStatus.CONFLICT);
		}else {
			Categoria categoria = categoriaServiceImpl.findById(id);
			if(categoria.getTemas() == null) {
				new ResponseEntity<List<Tema>>(HttpStatus.CONFLICT);
			}
			return new ResponseEntity<List<Tema>>(categoria.getTemas(), HttpStatus.OK);
		}		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> findById(@PathVariable Integer id) {
		if(temaServiceImpl.findById(id)==null) {
			return new ResponseEntity<Tema>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Tema>(temaServiceImpl.findById(id),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> insert(String titulo, Integer idUsuario, Boolean cerrado, String texto, Integer idCategoria, String fecha) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(titulo + idCategoria + idUsuario + cerrado + texto + fecha );
		Tema tema = new Tema();
		tema.setTitulo(titulo);
		tema.setUsuario(usuarioServiceImpl.findById(idUsuario));
		tema.setCerrado(cerrado);
		tema.setTexto(texto);
		tema.setCategoria(categoriaServiceImpl.findById(idCategoria));
		Date date = simpleDateFormat.parse(fecha);
		System.out.println("\n\n\n\n" + date);
		tema.setFecha(date);
		temaServiceImpl.create(tema);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<Void> edit(String titulo, Integer idUsuario, Boolean cerrado, String texto, Integer idCategoria, String fecha, Integer id) throws ParseException{
		if (temaServiceImpl.findById(id) == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Tema tema = temaServiceImpl.findById(id);
		tema.setTitulo(titulo);
		tema.setUsuario(usuarioServiceImpl.findById(idUsuario));
		tema.setCerrado(cerrado);
		tema.setTexto(texto);
		tema.setCategoria(categoriaServiceImpl.findById(idCategoria));
		tema.setFecha(simpleDateFormat.parse(fecha));
		temaServiceImpl.create(tema);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		if(temaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		else {
			temaServiceImpl.remove(temaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
