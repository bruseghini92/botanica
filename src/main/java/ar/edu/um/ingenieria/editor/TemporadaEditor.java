package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.controller.seguimiento.SeguimientoController;
import ar.edu.um.ingenieria.dto.TemporadaDTO;
import ar.edu.um.ingenieria.manager.TemporadaManager;

public class TemporadaEditor extends PropertyEditorSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);
	
	@Autowired
	private TemporadaManager temporadaManager;

	public TemporadaEditor(TemporadaManager temporadaManager) {
		this.temporadaManager = temporadaManager;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		logger.info("Text TEMPORADA EDITOR{" + text + "}");
		Integer id = Integer.parseInt(text);
		logger.info("ID TEMPORADA EDITOR{" + id + "}");
		TemporadaDTO temporadaDTO = id == 0 ? null : temporadaManager.findById(id);
		logger.info("TEMPORADADTO TEMPORADA EDITOR{" + temporadaDTO + "}");
		setValue(temporadaDTO);
	}
}
