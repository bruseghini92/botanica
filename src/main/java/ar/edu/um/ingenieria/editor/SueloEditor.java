package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.um.ingenieria.dto.SueloDTO;
import ar.edu.um.ingenieria.manager.SueloManager;

public class SueloEditor extends PropertyEditorSupport {
	@Autowired
	private SueloManager sueloManager;

	public SueloEditor(SueloManager sueloManager) {
		this.sueloManager = sueloManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		SueloDTO sueloDTO = id == 0 ? null : sueloManager.findById(id);
		setValue(sueloDTO);
	}
}
