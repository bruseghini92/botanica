package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;

public class PlantaEditor extends PropertyEditorSupport {
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	public PlantaEditor(PlantaServiceImpl plantaServiceImpl) {
		this.plantaServiceImpl = plantaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Planta planta = plantaServiceImpl.findById(Integer.parseInt(text));
		setValue(planta);
	}
}