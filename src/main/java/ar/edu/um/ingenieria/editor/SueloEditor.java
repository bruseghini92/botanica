package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;


import ar.edu.um.ingenieria.domain.Suelo;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;

public class SueloEditor extends PropertyEditorSupport {
	@Autowired
	private SueloServiceImpl sueloService;

	public SueloEditor(SueloServiceImpl sueloService) {
		this.sueloService = sueloService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Suelo suelo = sueloService.findById(Integer.parseInt(text));
		setValue(suelo);
	}
}
