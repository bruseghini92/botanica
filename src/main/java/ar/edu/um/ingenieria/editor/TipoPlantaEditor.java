package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.TipoPlanta;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

public class TipoPlantaEditor extends PropertyEditorSupport {
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;

	public TipoPlantaEditor(TipoPlantaServiceImpl tipoPlantaServiceImpl) {
		this.tipoPlantaServiceImpl = tipoPlantaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TipoPlanta tipoPlanta = tipoPlantaServiceImpl.findById(Integer.parseInt(text));
		setValue(tipoPlanta);
	}
}