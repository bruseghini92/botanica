package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.service.impl.PersonaServiceImpl;

public class PersonaEditor extends PropertyEditorSupport {
	@Autowired
	private PersonaServiceImpl personaServiceImpl;

	public PersonaEditor(PersonaServiceImpl personaServiceImpl) {
		this.personaServiceImpl = personaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Persona persona = personaServiceImpl.findById(Integer.parseInt(text));
		setValue(persona);
	}
}