package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.um.ingenieria.dto.RolDTO;
import ar.edu.um.ingenieria.manager.RolManager;

public class RolEditor extends PropertyEditorSupport {
	@Autowired
	private RolManager rolManager;

	public RolEditor(RolManager rolManager) {
		this.rolManager = rolManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		RolDTO rolDTO = id == 0 ? null : rolManager.findById(id);
		setValue(rolDTO);
	}
}
