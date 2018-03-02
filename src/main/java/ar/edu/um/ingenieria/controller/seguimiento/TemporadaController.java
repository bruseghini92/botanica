package ar.edu.um.ingenieria.controller.seguimiento;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Temporada;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;

@RestController
@RequestMapping("/temporadas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class TemporadaController {

		final static Logger logger = Logger.getLogger(TemporadaController.class);
		
		@Autowired
		private TemporadaServiceImpl temporadaServiceImpl;
		
		@GetMapping
		public List<Temporada> getIndex(){
			logger.info("Datos de Temporadas:{"+temporadaServiceImpl.findAll()+"}");
			return temporadaServiceImpl.findAll();
		}
		
		@GetMapping("/{id}")
		public Temporada getIndex (Integer id){
			logger.info("Datos de Temporadas:"+temporadaServiceImpl.findById(id));
			return temporadaServiceImpl.findById(id);
		}
		
}
