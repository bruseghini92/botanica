package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.manager.EstadoManager;

public class EstadoEditor extends PropertyEditorSupport {
	@Autowired
	private EstadoManager estadoManager;

	public EstadoEditor(EstadoManager estadoManager) {
		this.estadoManager = estadoManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		EstadoDTO estadoDTO = id == 0 ? null : estadoManager.findById(id);
		setValue(estadoDTO);
	}
}
