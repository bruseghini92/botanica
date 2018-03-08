package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.manager.PlantaManager;

public class PlantaEditor extends PropertyEditorSupport {

	@Autowired
	private PlantaManager plantaManager;

	public PlantaEditor(PlantaManager plantaManager) {
		this.plantaManager = plantaManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int id = Integer.parseInt(text);
		PlantaDTO plantaDTO = id == 0 ? null : plantaManager.findById(id);
		setValue(plantaDTO);
	}
}