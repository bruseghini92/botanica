package ar.edu.um.ingenieria.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.domain.Tarea;

@Service
public class SeguimientoServiceImpl extends ServiceImpl<Seguimiento, Integer>{
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	@Autowired
	private EtapaServiceImpl etapaServiceImpl;
	@Autowired
	private TareaServiceImpl tareaServiceImpl;
	
	@Override
	public Seguimiento create(Seguimiento entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Seguimiento entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Seguimiento update(Seguimiento entity) {
		// TODO Auto-generated method stub
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
	
	public void create(Integer usuario, Integer planta, Integer estado) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Seguimiento seguimiento = new Seguimiento();
		if (estado == 1)
		{
		seguimiento.setEtapa(etapaServiceImpl.findById(1));
		seguimiento.setTarea(tareaServiceImpl.findById(1));
		seguimiento.setEstado(estadoServiceImpl.findById(estado));
		} else if (estado == 2){
			seguimiento.setEtapa(etapaServiceImpl.findById(6));
			seguimiento.setTarea(tareaServiceImpl.findById(7));
			seguimiento.setEstado(estadoServiceImpl.findById(estado));
		} else {
			seguimiento.setEtapa(etapaServiceImpl.findById(3));
			seguimiento.setEstado(estadoServiceImpl.findById(estado));
		}
		seguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		seguimiento.setPlanta(plantaServiceImpl.findById(planta));
		seguimiento.setFechaInicio(calendar.getTime());
		seguimiento.setUltimoRiego(calendar.getTime());
		calendar.add(Calendar.HOUR, seguimiento.getPlanta().getTiempoRiego().getHours());
		seguimiento.setProximoRiego(calendar.getTime());
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public void update (Integer usuario, Integer planta, Integer estado, Integer tarea, Integer etapa,Integer seguimiento)
	{
		Seguimiento nativeSeguimiento = seguimientoServiceImpl.findById(seguimiento);
		nativeSeguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		nativeSeguimiento.setTarea(tareaServiceImpl.findById(tarea));
		nativeSeguimiento.setEtapa(etapaServiceImpl.findById(etapa));
		nativeSeguimiento.setEstado(estadoServiceImpl.findById(estado));
		nativeSeguimiento.setPlanta(plantaServiceImpl.findById(planta));
		seguimientoServiceImpl.create(nativeSeguimiento);
	}
	
	public void regar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		long tiempoActual = calendar.getTimeInMillis()-10800000;
		calendar.setTimeInMillis(tiempoActual);
		seguimiento.setUltimoRiego(calendar.getTime());
		calendar.add(Calendar.HOUR, seguimiento.getPlanta().getTiempoRiego().getHours());
		seguimiento.setProximoRiego(calendar.getTime());
		Integer tareaActual = seguimiento.getTarea().getId(), etapaActual = seguimiento.getEtapa().getId();
		Tarea tarea = seguimiento.getTarea();
		List<Tarea> tareas = seguimiento.getEtapa().getTareas();
		Integer tamanio = seguimiento.getEtapa().getTareas().size()-1;
		Integer ultimaTarea = tareas.get(tamanio).getId();
		if (tareaActual == ultimaTarea && Calendar.getInstance(TimeZone.getTimeZone("-0300")).before(seguimiento.getFechaAbono())) {
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual+1));
			List<Tarea> tareasNew = etapaServiceImpl.findById(etapaActual+1).getTareas();
			seguimiento.setTarea(tareasNew.get(1));
		} else {
			Integer index = seguimiento.getEtapa().getTareas().indexOf(tarea);
			seguimiento.setTarea(tareas.get(index));
		}
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public void sueloPreparado (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		if (seguimiento.getTarea().getId() == 1) {
		Integer tareaActual = seguimiento.getTarea().getId(), etapaActual = seguimiento.getEtapa().getId();
		Tarea tarea = seguimiento.getTarea();
		List<Tarea> tareas = seguimiento.getEtapa().getTareas();
		Integer tamanio = seguimiento.getEtapa().getTareas().size()-1;
		Integer ultimaTarea = tareas.get(tamanio).getId();
		if (tareaActual == ultimaTarea) {
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual+1));
			List<Tarea> tareasNew = etapaServiceImpl.findById(etapaActual+1).getTareas();
			seguimiento.setTarea(tareasNew.get(1));
		} else {
			Integer index = seguimiento.getEtapa().getTareas().indexOf(tarea);
			seguimiento.setTarea(tareas.get(index+1));
		}
		seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void sembrado (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		if (seguimiento.getTarea().getId() == 2) {
		Integer tareaActual = seguimiento.getTarea().getId(), etapaActual = seguimiento.getEtapa().getId();
		Tarea tarea = seguimiento.getTarea();
		List<Tarea> tareas = seguimiento.getEtapa().getTareas();
		Integer tamanio = seguimiento.getEtapa().getTareas().size()-1;
		Integer ultimaTarea = tareas.get(tamanio).getId();
		if (tareaActual == ultimaTarea) {
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual+1));
			List<Tarea> tareasNew = etapaServiceImpl.findById(etapaActual+1).getTareas();
			seguimiento.setTarea(tareasNew.get(1));
		} else {
			Integer index = seguimiento.getEtapa().getTareas().indexOf(tarea);
			seguimiento.setTarea(tareas.get(index+1));
		}
		seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void transplantar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		if (seguimiento.getTarea().getId() == 3) {
		Integer tareaActual = seguimiento.getTarea().getId(), etapaActual = seguimiento.getEtapa().getId();
		Tarea tarea = seguimiento.getTarea();
		List<Tarea> tareas = seguimiento.getEtapa().getTareas();
		Integer tamanio = seguimiento.getEtapa().getTareas().size()-1;
		Integer ultimaTarea = tareas.get(tamanio).getId();
		if (tareaActual == ultimaTarea) {
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual+1));
			List<Tarea> tareasNew = etapaServiceImpl.findById(etapaActual+1).getTareas();
			seguimiento.setTarea(tareasNew.get(1));
		} else {
			Integer index = seguimiento.getEtapa().getTareas().indexOf(tarea);
			seguimiento.setTarea(tareas.get(index+1));
		}
		seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void abonar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Calendar timeNow = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.setTime(seguimiento.getFechaInicio());
		calendar.add(Calendar.YEAR, 1);
		Tarea tarea = seguimiento.getTarea();
		List<Tarea> tareas = seguimiento.getEtapa().getTareas();
		Integer tamanio = seguimiento.getEtapa().getTareas().size()-1;
		Integer ultimaTarea = tareas.get(tamanio).getId();
		Integer tareaActual = seguimiento.getTarea().getId(), etapaActual = seguimiento.getEtapa().getId();
		if (seguimiento.getTarea().getId() == 11 || seguimiento.getTarea().getId() == 12) {
		if (tareaActual == ultimaTarea) {
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual+1));
			seguimiento.setTarea(etapaServiceImpl.findById(etapaActual+1).getTareas().get(0));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		} else {
			Integer index = seguimiento.getEtapa().getTareas().indexOf(tarea);
			seguimiento.setTarea(tareas.get(index+1));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		}
		}
		if (seguimiento.getTarea().getId() == 13 && calendar.before(Calendar.getInstance(TimeZone.getTimeZone("GTM-0300")))) {
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual+1));
			seguimiento.setTarea(etapaServiceImpl.findById(etapaActual+1).getTareas().get(1));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		} else if (seguimiento.getTarea().getId() == 13 && calendar.after(Calendar.getInstance(TimeZone.getTimeZone("GTM-0300")))){
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual));
			seguimiento.setTarea(etapaServiceImpl.findById(etapaActual).getTareas().get(0));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void podar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Calendar timeNow = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.setTime(seguimiento.getFechaInicio());
		calendar.add(Calendar.YEAR, 1);
		if (seguimiento.getTarea().getId() == 4 && seguimiento.getEtapa().getId() == 4 /*&& calendar.before(Calendar.getInstance(TimeZone.getTimeZone("GTM-0300")))*/) {
		Integer tareaActual = seguimiento.getTarea().getId(), etapaActual = seguimiento.getEtapa().getId();
		Tarea tarea = seguimiento.getTarea();
		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		seguimiento.setFechaPoda(calendar.getTime());
		List<Tarea> tareas = seguimiento.getEtapa().getTareas();
		Integer tamanio = seguimiento.getEtapa().getTareas().size()-1;
		Integer ultimaTarea = tareas.get(tamanio).getId();
		if (tareaActual == ultimaTarea) {
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual+1));
			List<Tarea> tareasNew = etapaServiceImpl.findById(etapaActual+1).getTareas();
			seguimiento.setTarea(tareasNew.get(1));
		} else {
			Integer index = seguimiento.getEtapa().getTareas().indexOf(tarea);
			seguimiento.setTarea(tareas.get(index+1));
		}
		if (seguimiento.getTarea().getId() == 4 && calendar.before(Calendar.getInstance(TimeZone.getTimeZone("GTM-0300")))) {
			seguimiento.setEtapa(etapaServiceImpl.findById(3));
			seguimiento.setTarea(tareaServiceImpl.findById(8));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		} else if (seguimiento.getTarea().getId() == 13 && calendar.after(Calendar.getInstance(TimeZone.getTimeZone("GTM-0300")))){
			seguimiento.setEtapa(etapaServiceImpl.findById(etapaActual));
			seguimiento.setTarea(etapaServiceImpl.findById(etapaActual).getTareas().get(0));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		}
		seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void cosechar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		if (seguimiento.getEtapa().getId() == 5) {
		seguimiento.setEtapa(etapaServiceImpl.findById(8));
		seguimiento.setTarea(tareaServiceImpl.findById(3));
		seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void UpdateStatus(Integer idUser) {
		List<Seguimiento> seguimientos = seguimientoServiceImpl.findByUser(idUser);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Calendar fecha = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.add(Calendar.DATE,-10);
		for (int i = 0; i < seguimientos.size();i++)
		{
			if (seguimientos.get(i).getUltimoRiego().before(calendar.getTime())) {
				seguimientos.get(i).setEstado(estadoServiceImpl.findById(3));
			}
			fecha.setTime(seguimientos.get(i).getFechaInicio());
			fecha.add(Calendar.YEAR, 1);
			long tiempoActual = calendar.getTimeInMillis();
			calendar.setTimeInMillis(tiempoActual);
			if (seguimientos.get(i).getEtapa().getId() == 1 && fecha.before(calendar.getTime())) {
				seguimientos.get(i).setEtapa(etapaServiceImpl.findById(4));
			}
		}
	}
	
	public List<Seguimiento> findByUser(Integer usuario)
	{
		return usuarioServiceImpl.findById(usuario).getSeguimiento();
	}

	public void delete(Integer id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(id);		
		seguimientoServiceImpl.remove(seguimiento);
	}
}
