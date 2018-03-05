package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;

public class EstadoEditor extends PropertyEditorSupport {
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	public EstadoEditor(EstadoServiceImpl estadoServiceImpl) {
		this.estadoServiceImpl = estadoServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Estado estado = estadoServiceImpl.findById(Integer.parseInt(text));
		setValue(estado);
	}
}
