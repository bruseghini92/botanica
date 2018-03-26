package ar.edu.um.ingenieria.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.domain.Tarea;

@Service
public class SeguimientoServiceImpl extends ServiceImpl<Seguimiento, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoServiceImpl.class);

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired
	private EtapaServiceImpl etapaServiceImpl;
	
	@Autowired
	private TareaServiceImpl tareaServiceImpl;

	@SuppressWarnings("deprecation")
	@Override
	public Seguimiento create(Seguimiento seguimiento) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.add(Calendar.HOUR, -3);
		if (seguimiento.getEstado().getId() == 1) {
			seguimiento.setEtapa(etapaServiceImpl.findById(1));
			seguimiento.setTarea(tareaServiceImpl.findById(1));
		} else if (seguimiento.getEstado().getId() == 2) {
			seguimiento.setEtapa(etapaServiceImpl.findById(6));
			seguimiento.setTarea(tareaServiceImpl.findById(7));
		}
		seguimiento.setFechaInicio(calendar.getTime());
		seguimiento.setUltimoRiego(calendar.getTime());
		calendar.add(Calendar.YEAR, 3);
		seguimiento.setFechaCosecha(calendar.getTime());
		calendar.add(Calendar.YEAR, -3);
		calendar.add(Calendar.MONTH, 1);
		seguimiento.setFechaAbono(calendar.getTime());
		calendar.add(Calendar.MONTH, -1);
		calendar.add(Calendar.MONTH, 6);
		seguimiento.setFechaPoda(calendar.getTime());
		calendar.add(Calendar.MONTH, -6);
		calendar.add(Calendar.HOUR, seguimiento.getPlanta().getTiempoRiego().getHours());
		seguimiento.setProximoRiego(calendar.getTime());
		super.create(seguimiento);
		return seguimiento;
	}

	@Override
	public void remove(Seguimiento entity) {
		logger.info("Seguimiento Remove");
		super.remove(entity);
	}

	@Override
	public Seguimiento update(Seguimiento entity) {
		return super.update(entity);
	}

	@Override
	public Seguimiento findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Seguimiento> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@SuppressWarnings("deprecation")
	public void regar(Seguimiento seguimiento) {
		logger.info("Método regar SEGUIMIENTO:" + seguimiento);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
		calendar.add(Calendar.HOUR, -3);
		Integer etapaActual = seguimiento.getEtapa().getId();
		Tarea tarea = seguimiento.getTarea();
		List<Tarea> tareas = seguimiento.getEtapa().getTareas();
		List<Etapa> etapas = seguimiento.getEstado().getEtapas();
		seguimiento.setUltimoRiego(calendar.getTime());
		calendar.add(Calendar.HOUR, seguimiento.getPlanta().getTiempoRiego().getHours());
		seguimiento.setProximoRiego(calendar.getTime());
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
		calendar.add(Calendar.HOUR, -3);
		Calendar abono = Calendar.getInstance();
		abono.setTime(seguimiento.getFechaAbono());
		abono.add(Calendar.MONTH, 1);
		Calendar poda = Calendar.getInstance();
		poda.setTime(seguimiento.getFechaPoda());
		abono.add(Calendar.YEAR, 1);
		Calendar cosecha = Calendar.getInstance();
		cosecha.setTime(seguimiento.getFechaCosecha());
		cosecha.add(Calendar.MONTH, 6);
		if (abono.before(calendar)
				&& tareas.indexOf(seguimiento.getTarea()) < seguimiento.getEtapa().getTareas().size()) {
			seguimiento.setTarea(tareas.get(tareas.indexOf(tarea) + 1));
		} else if (poda.before(calendar) && seguimiento.getEtapa().getId() == 3) {
			seguimiento.setEtapa(etapas.get(etapas.indexOf(etapaServiceImpl.findById(etapaActual)) + 1));
			seguimiento.setTarea(seguimiento.getEtapa().getTareas().get(0));
		} else if (cosecha.before(calendar) && seguimiento.getEtapa().getId() == 3
				&& seguimiento.getPlanta().getTipo().getId() == 1
				|| cosecha.before(calendar) && seguimiento.getEtapa().getId() == 3
						&& seguimiento.getPlanta().getTipo().getId() == 2) {
			seguimiento.setEtapa(etapaServiceImpl.findById(5));
			seguimiento.setTarea(seguimiento.getEtapa().getTareas().get(0));
		}
		super.update(seguimiento);
	}

	public void sueloPreparado(Seguimiento seguimiento) {
		logger.info("Método prepararSuelo SEGUIMIENTO:" + seguimiento);
		seguimiento.setTarea(tareaServiceImpl.findById(2));
		super.update(seguimiento);
	}

	public void sembrado(Seguimiento seguimiento) {
		logger.info("Método sembrado SEGUIMIENTO:" + seguimiento);
		seguimiento.setTarea(tareaServiceImpl.findById(6));
		super.update(seguimiento);
	}

	public void transplantar(Seguimiento seguimiento) {
		logger.info("Método sembrado SEGUIMIENTO:" + seguimiento);
		seguimiento.setTarea(tareaServiceImpl.findById(7));
		super.update(seguimiento);
	}

	public void abonar(Seguimiento seguimiento) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Calendar timeNow = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		timeNow.add(Calendar.HOUR, -3);
		calendar.add(Calendar.HOUR, -3);
		calendar.add(Calendar.YEAR, 1);
		List<Etapa> etapas = seguimiento.getEstado().getEtapas();
		Integer etapaActual = seguimiento.getEtapa().getId();
		if (seguimiento.getTarea().getId() == 11 || seguimiento.getTarea().getId() == 12) {
			seguimiento.setEtapa(etapas.get(etapas.indexOf(etapaServiceImpl.findById(etapaActual)) + 1));
			seguimiento.setTarea(seguimiento.getEtapa().getTareas().get(0));
			seguimiento.setFechaAbono(timeNow.getTime());
			super.update(seguimiento);
		} else if (seguimiento.getTarea().getId() == 13) {
			seguimiento.setTarea(tareaServiceImpl.findById(8));
			seguimiento.setFechaAbono(timeNow.getTime());
			super.update(seguimiento);
		}
	}

	public void podar(Seguimiento seguimiento) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		if (seguimiento.getTarea().getId() == 4 && seguimiento.getEtapa().getId() == 4) {
			seguimiento.setFechaPoda(calendar.getTime());
			calendar.add(Calendar.YEAR, 1);
			if (seguimiento.getTarea().getId() == 4) {
				seguimiento.setEtapa(etapaServiceImpl.findById(3));
				seguimiento.setTarea(tareaServiceImpl.findById(8));
				seguimiento.setFechaPoda(calendar.getTime());
				super.update(seguimiento);
			}
			super.update(seguimiento);
		}
	}

	public void cosechar(Seguimiento seguimiento) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.add(Calendar.HOUR, -3);
		calendar.add(Calendar.YEAR, 1);
		seguimiento.setEtapa(etapaServiceImpl.findById(3));
		seguimiento.setTarea(tareaServiceImpl.findById(8));
		seguimiento.setFechaCosecha(calendar.getTime());
		super.update(seguimiento);
	}

	public List<Seguimiento> findByUser(Integer usuario) {
		return usuarioServiceImpl.findById(usuario).getSeguimiento();
	}
	
	public List<Seguimiento> findByPlanta(Integer planta) {
		return plantaServiceImpl.findById(planta).getSeguimiento();
	}

}
