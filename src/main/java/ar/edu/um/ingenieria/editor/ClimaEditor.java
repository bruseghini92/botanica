package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Clima;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;

public class ClimaEditor extends PropertyEditorSupport {
	@Autowired
	private ClimaServiceImpl climaService;

	public ClimaEditor(ClimaServiceImpl climaService) {
		this.climaService = climaService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Clima clima = climaService.findById(Integer.parseInt(text));
		setValue(clima);
	}
}
