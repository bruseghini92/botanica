package ar.edu.um.ingenieria.controller.ventas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ar.edu.um.ingenieria.domain.TipoVenta;
import ar.edu.um.ingenieria.domain.Venta;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@RestController
@RequestMapping("/ventas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class VentasController {
	@Autowired
	private VentaServiceImpl ventaServiceImpl;
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;
	
	@GetMapping("/")
	public ResponseEntity<List<Venta>> findAll() {
		return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venta> findById(@PathVariable Integer id) {
		if(ventaServiceImpl.findById(id)==null)
			return new ResponseEntity<Venta>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Venta>(ventaServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@GetMapping("/tipoventa/{id}")
	public ResponseEntity<List<Venta>> findByTipo(@PathVariable Integer id) {
		if (tipoVentaServiceImpl.findById(id) == null) {
			return new ResponseEntity<List<Venta>>(ventaServiceImpl.findAll(), HttpStatus.OK);
		} else {
			TipoVenta tipoVenta = tipoVentaServiceImpl.findById(id);
			if(tipoVenta.getVentas() == null) {
				new ResponseEntity<List<Venta>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Venta>>(tipoVenta.getVentas(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> insert(String producto, String descripcion, Integer usuarioId , Boolean cerrado, String fecha, Integer tipoVentaId) throws ParseException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//para transformar la hora de string a date
		Venta venta=new Venta();
		venta.setProducto(producto);
		venta.setDescripcion(descripcion);
		venta.setUsuario(usuarioServiceImpl.findById(usuarioId));
		venta.setCerrado(cerrado);
		venta.setFecha(simpleDateFormat.parse(fecha));
		System.out.println("\n\n"+venta.getFecha()+"\n\n");
		venta.setTipoVenta(tipoVentaServiceImpl.findById(tipoVentaId));
		ventaServiceImpl.create(venta);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/edit/")
	public ResponseEntity<Void> edit(Integer id, String producto, String descripcion, Integer usuarioId , Boolean cerrado, String fecha, Integer tipoVentaId) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(ventaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else {
			Venta venta=ventaServiceImpl.findById(id);
			venta.setProducto(producto);
			venta.setDescripcion(descripcion);
			venta.setUsuario(usuarioServiceImpl.findById(usuarioId));
			venta.setCerrado(cerrado);
			venta.setFecha(simpleDateFormat.parse(fecha));
			System.out.println("\n\n"+venta.getFecha()+"\n\n");
			venta.setTipoVenta(tipoVentaServiceImpl.findById(tipoVentaId));
			ventaServiceImpl.update(venta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/close/{id}")
	public ResponseEntity<Void>  close(@PathVariable Integer id) {
		if(ventaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else {
			Venta venta = ventaServiceImpl.findById(id);
			venta.setCerrado(true);
			ventaServiceImpl.update(ventaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/open/{id}")
	public ResponseEntity<Void>  open(@PathVariable Integer id) {
		if(ventaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		else {
			Venta venta = ventaServiceImpl.findById(id);
			venta.setCerrado(false);
			ventaServiceImpl.update(ventaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	/*@DeleteMapping("/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Integer id) {
		if(ventaServiceImpl.findById(id)==null)
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		else {
			ventaServiceImpl.remove(ventaServiceImpl.findById(id));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}*/
	
}
