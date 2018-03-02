package ar.edu.um.ingenieria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;

@Controller
@RequestMapping("/plantas")
public class PlantasController {
	@Autowired
	private SueloServiceImpl sueloService;
	@Autowired
	private ClimaServiceImpl climaService;
	
	@RequestMapping(value = "/plantas", method = RequestMethod.GET)
	public String registracion(Model model) {
		PlantaDTO planta = new PlantaDTO();
		model.addAttribute("suelos", sueloService.findAll());
		model.addAttribute("climas", climaService.findAll());
		model.addAttribute("planta", planta);
		return "plantas";
	}

	@RequestMapping(value = "/plantas", method = RequestMethod.POST)
	public String formNuevo(@Valid @ModelAttribute("planta") PlantaDTO planta, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			// Si no tiene errores de validacion se guarda en la BD
			// y se redirecciona a otro pagina
			// return "redirect:/otrapagina";
		} else {
			// si contiene errores muestra nuevamente la vista
		//	model.addAttribute("suelos", sueloService.findAll());
		//	model.addAttribute("climas", climaService.findAll());
		}
		return "plantas";
	}

	@InitBinder("planta")
	private void initBinder(WebDataBinder binder) {
	/*	binder.registerCustomEditor(Clima.class, new ClimaEditor(this.climaService));
		binder.registerCustomEditor(Suelo.class, new SueloEditor(this.sueloService));*/
	}

}
