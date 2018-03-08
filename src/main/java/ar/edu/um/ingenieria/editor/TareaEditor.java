package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.TareaDTO;
import ar.edu.um.ingenieria.manager.TareaManager;

public class TareaEditor extends PropertyEditorSupport {
	@Autowired
	private TareaManager tareaManager;

	public TareaEditor(TareaManager tareaManager) {
		this.tareaManager = tareaManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		TareaDTO tareaDTO = id == 0 ? null : tareaManager.findById(id);
		setValue(tareaDTO);
	}
}
