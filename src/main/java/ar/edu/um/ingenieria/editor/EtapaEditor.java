package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.service.impl.EtapaServiceImpl;

public class EtapaEditor extends PropertyEditorSupport {
	@Autowired
	private EtapaServiceImpl etapaServiceImpl;

	public EtapaEditor(EtapaServiceImpl etapaServiceImpl) {
		this.etapaServiceImpl = etapaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Etapa etapa = etapaServiceImpl.findById(Integer.parseInt(text));
		setValue(etapa);
	}
}
