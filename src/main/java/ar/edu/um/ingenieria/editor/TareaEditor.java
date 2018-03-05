package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.service.impl.TareaServiceImpl;

public class TareaEditor extends PropertyEditorSupport {
	@Autowired
	private TareaServiceImpl tareaServiceImpl;

	public TareaEditor(TareaServiceImpl tareaServiceImpl) {
		this.tareaServiceImpl = tareaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Tarea tarea = tareaServiceImpl.findById(Integer.parseInt(text));
		setValue(tarea);
	}
}