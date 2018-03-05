package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Respuesta;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;

public class RespuestaEditor extends PropertyEditorSupport {
	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;

	public RespuestaEditor(RespuestaServiceImpl respuestaServiceImpl) {
		this.respuestaServiceImpl = respuestaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Respuesta respuesta = respuestaServiceImpl.findById(Integer.parseInt(text));
		setValue(respuesta);
	}
}