package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

public class TipoVentaEditor extends PropertyEditorSupport {
	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	public TipoVentaEditor(TipoVentaServiceImpl tipoVentaServiceImpl) {
		this.tipoVentaServiceImpl = tipoVentaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TipoVenta tipoVenta = tipoVentaServiceImpl.findById(Integer.parseInt(text));
		setValue(tipoVenta);
	}
}
