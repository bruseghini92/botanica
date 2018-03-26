package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.TemporadaDTO;
import ar.edu.um.ingenieria.manager.TemporadaManager;

public class TemporadaEditor extends PropertyEditorSupport {

	@Autowired
	private TemporadaManager temporadaManager;

	public TemporadaEditor(TemporadaManager temporadaManager) {
		this.temporadaManager = temporadaManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Integer id = Integer.parseInt(text);
		TemporadaDTO temporadaDTO = id == 0 ? null : temporadaManager.findById(id);
		setValue(temporadaDTO);
	}
}
