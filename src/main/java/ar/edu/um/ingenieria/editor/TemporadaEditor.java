package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Temporada;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;

public class TemporadaEditor extends PropertyEditorSupport {
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;

	public TemporadaEditor(TemporadaServiceImpl temporadaServiceImpl) {
		this.temporadaServiceImpl = temporadaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Temporada temporada = temporadaServiceImpl.findById(Integer.parseInt(text));
		setValue(temporada);
	}
}
