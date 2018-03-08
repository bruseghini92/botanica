package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.manager.UsuarioManager;

public class UsuarioEditor extends PropertyEditorSupport {

	@Autowired
	private UsuarioManager usuarioManager;

	public UsuarioEditor(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		UsuarioDTO usuarioDTO = id == 0 ? null : usuarioManager.findById(id);
		setValue(usuarioDTO);
	}
}