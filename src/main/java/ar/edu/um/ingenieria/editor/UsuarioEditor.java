package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

public class UsuarioEditor extends PropertyEditorSupport {
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	public UsuarioEditor(UsuarioServiceImpl usuarioServiceImpl) {
		this.usuarioServiceImpl = usuarioServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Usuario usuario = usuarioServiceImpl.findById(Integer.parseInt(text));
		setValue(usuario);
	}
}