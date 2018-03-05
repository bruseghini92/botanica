package ar.edu.um.ingenieria.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Etapa;
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
		calendar.add(Calendar.HOUR, -3);
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
		}
		seguimiento.setUsuario(usuarioServiceImpl.findById(usuario));
		seguimiento.setPlanta(plantaServiceImpl.findById(planta));
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
		if (abono.before(calendar) && tareas.indexOf(seguimiento.getTarea()) < seguimiento.getEtapa().getTareas().size()) {
			seguimiento.setTarea(tareas.get(tareas.indexOf(tarea)+1));
		} else if(poda.before(calendar) && seguimiento.getEtapa().getId() == 3){
			seguimiento.setEtapa(etapas.get(etapas.indexOf(etapaServiceImpl.findById(etapaActual))+1));
			seguimiento.setTarea(seguimiento.getEtapa().getTareas().get(0));
			seguimientoServiceImpl.create(seguimiento);
		}else if(cosecha.before(calendar) && seguimiento.getEtapa().getId() == 3 && seguimiento.getPlanta().getTipo().getId() == 1
				|| cosecha.before(calendar) && seguimiento.getEtapa().getId() == 3 && seguimiento.getPlanta().getTipo().getId() == 2){
			seguimiento.setEtapa(etapaServiceImpl.findById(5));
			seguimiento.setTarea(seguimiento.getEtapa().getTareas().get(0));
			seguimientoServiceImpl.create(seguimiento);
		}else {
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
		timeNow.add(Calendar.HOUR, -3);
		calendar.add(Calendar.HOUR, -3);
		calendar.add(Calendar.YEAR, 1);
		List<Etapa> etapas = seguimiento.getEstado().getEtapas();
		Integer etapaActual = seguimiento.getEtapa().getId();
		if (seguimiento.getTarea().getId() == 11 || seguimiento.getTarea().getId() == 12) {
			seguimiento.setEtapa(etapas.get(etapas.indexOf(etapaServiceImpl.findById(etapaActual))+1));
			seguimiento.setTarea(seguimiento.getEtapa().getTareas().get(0));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		} else if (seguimiento.getTarea().getId() == 13){
			seguimiento.setTarea(tareaServiceImpl.findById(8));
			seguimiento.setFechaAbono(timeNow.getTime());
			seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void podar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		if (seguimiento.getTarea().getId() == 4 && seguimiento.getEtapa().getId() == 4) {
		seguimiento.setFechaPoda(calendar.getTime());
		calendar.add(Calendar.YEAR, 1);
		if (seguimiento.getTarea().getId() == 4) {
			seguimiento.setEtapa(etapaServiceImpl.findById(3));
			seguimiento.setTarea(tareaServiceImpl.findById(8));
			seguimiento.setFechaPoda(calendar.getTime());
			seguimientoServiceImpl.create(seguimiento);
		} 
		seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void cosechar (Integer seguimiento_id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(seguimiento_id);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.add(Calendar.HOUR, -3);
		calendar.add(Calendar.YEAR, 1);
		if (seguimiento.getEtapa().getId() == 5) {
		seguimiento.setEtapa(etapaServiceImpl.findById(3));
		seguimiento.setTarea(tareaServiceImpl.findById(8));
		seguimiento.setFechaCosecha(calendar.getTime());
		seguimientoServiceImpl.create(seguimiento);
		}
	}
	
	public void UpdateStatus(Integer idUser) {
		List<Seguimiento> seguimientos = seguimientoServiceImpl.findByUser(idUser);
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		calendar.add(Calendar.HOUR, -3);
		Calendar fecha = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		fecha.add(Calendar.HOUR, -3);
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
