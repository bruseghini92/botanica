package ar.edu.um.ingenieria.controller.seguimiento;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Suelo;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;

@RestController
@RequestMapping("/suelos")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class SueloController {
	@Autowired
	private SueloServiceImpl sueloServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(SueloController.class);
		@GetMapping
		public List<Suelo> indexPage() {
			logger.info("datos de seguimiento: {}", sueloServiceImpl.findAll());
			return sueloServiceImpl.findAll();
		}

		@GetMapping("/{id}")
		public Suelo show(@PathVariable Integer id) {
			logger.info("datos de seguimiento: {}", sueloServiceImpl.findById(id));
			return sueloServiceImpl.findById(id);
		}
}
