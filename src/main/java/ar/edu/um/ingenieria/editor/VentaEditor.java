package ar.edu.um.ingenieria.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

public class VentaEditor extends PropertyEditorSupport {
	@Autowired
	private VentaServiceImpl ventaServiceImpl;

	public VentaEditor(VentaServiceImpl ventaServiceImpl) {
		this.ventaServiceImpl = ventaServiceImpl;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Venta venta = ventaServiceImpl.findById(Integer.parseInt(text));
		setValue(venta);
	}
}
