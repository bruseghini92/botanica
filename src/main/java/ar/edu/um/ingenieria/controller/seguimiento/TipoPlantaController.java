package ar.edu.um.ingenieria.controller.seguimiento;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.TipoPlanta;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@RestController
@RequestMapping("/tipo_plantas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class TipoPlantaController {

		final static Logger logger = Logger.getLogger(TipoPlantaController.class);
		
		@Autowired
		private TipoPlantaServiceImpl tipoPlantaServiceImpl;
		
		@GetMapping
		public List<TipoPlanta> getIndex(){
			logger.info("Datos de Temporadas:{"+tipoPlantaServiceImpl.findAll()+"}");
			return tipoPlantaServiceImpl.findAll();
		}
		
		@GetMapping("/{id}")
		public TipoPlanta getIndex (Integer id){
			logger.info("Datos de Temporadas:"+tipoPlantaServiceImpl.findById(id));
			return tipoPlantaServiceImpl.findById(id);
		}
}