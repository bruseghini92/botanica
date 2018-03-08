package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.TipoPlantaDTO;
import ar.edu.um.ingenieria.manager.TipoPlantaManager;

public class TipoPlantaEditor extends PropertyEditorSupport {
	@Autowired
	private TipoPlantaManager tipoPlantaManager;

	public TipoPlantaEditor(TipoPlantaManager tipoPlantaManager) {
		this.tipoPlantaManager = tipoPlantaManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		TipoPlantaDTO tipoPlantaDTO = id == 0 ? null : tipoPlantaManager.findById(id);
		setValue(tipoPlantaDTO);
	}
}
