package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.ClimaDTO;
import ar.edu.um.ingenieria.manager.ClimaManager;

public class ClimaEditor extends PropertyEditorSupport {
	@Autowired
	private ClimaManager climaManager;

	public ClimaEditor(ClimaManager climaManager) {
		this.climaManager = climaManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		ClimaDTO climaDTO = id == 0 ? null : climaManager.findById(id);
		setValue(climaDTO);
	}
}
