package ar.edu.um.ingenieria.controller.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

@RestController
@RequestMapping("/tipoventas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class TipoVentaController {

	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	@GetMapping("/")
	public ResponseEntity<List<TipoVenta>> findAll() {
		return new ResponseEntity<List<TipoVenta>>(tipoVentaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoVenta> findById(@PathVariable Integer id) {
		if(tipoVentaServiceImpl.findById(id)==null)
			return new ResponseEntity<TipoVenta>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<TipoVenta>(tipoVentaServiceImpl.findById(id),HttpStatus.OK);
	}
}

