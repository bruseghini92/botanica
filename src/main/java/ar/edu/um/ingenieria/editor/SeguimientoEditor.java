package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

public class SeguimientoEditor extends PropertyEditorSupport {
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	public SeguimientoEditor(SeguimientoServiceImpl seguimientoServiceImpl) {
		this.seguimientoServiceImpl = seguimientoServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(Integer.parseInt(text));
		setValue(seguimiento);
	}
}