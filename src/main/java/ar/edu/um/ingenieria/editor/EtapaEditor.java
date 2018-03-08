package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.EtapaDTO;
import ar.edu.um.ingenieria.manager.EtapaManager;

public class EtapaEditor extends PropertyEditorSupport {
	@Autowired
	private EtapaManager etapaManager;

	public EtapaEditor(EtapaManager etapaManager) {
		this.etapaManager = etapaManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		EtapaDTO etapaDTO = id == 0 ? null : etapaManager.findById(id);
		setValue(etapaDTO);
	}
}
