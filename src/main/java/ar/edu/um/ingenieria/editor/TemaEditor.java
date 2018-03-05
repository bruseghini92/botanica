package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Tema;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

public class TemaEditor extends PropertyEditorSupport {
	@Autowired
	private TemaServiceImpl temaServiceImpl;

	public TemaEditor(TemaServiceImpl temaServiceImpl) {
		this.temaServiceImpl = temaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Tema tema = temaServiceImpl.findById(Integer.parseInt(text));
		setValue(tema);
	}
}