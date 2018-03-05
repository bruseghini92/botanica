package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Categoria;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;

public class CategoriaEditor extends PropertyEditorSupport {
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;

	public CategoriaEditor(CategoriaServiceImpl categoriaServiceImpl) {
		this.categoriaServiceImpl = categoriaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Categoria categoria = categoriaServiceImpl.findById(Integer.parseInt(text));
		setValue(categoria);
	}
}