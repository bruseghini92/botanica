package ar.edu.um.ingenieria.controller.foro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;

@RestController
@RequestMapping("/foro/categoria")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class CategoriaController {

	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		if (categoriaServiceImpl.findAll() == null) {
			return new ResponseEntity<List<Categoria>>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<List<Categoria>>(categoriaServiceImpl.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		if (categoriaServiceImpl.findById(id) == null) {
			return new ResponseEntity<Categoria>(HttpStatus.CONFLICT); 
		}
		return new ResponseEntity<Categoria>(categoriaServiceImpl.findById(id),HttpStatus.OK);
	}
}
