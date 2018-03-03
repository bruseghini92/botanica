package ar.edu.um.ingenieria.manager;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.convertor.EstadoConvertor;
import ar.edu.um.ingenieria.convertor.EtapaConvertor;
import ar.edu.um.ingenieria.convertor.PlantaConvertor;
import ar.edu.um.ingenieria.convertor.SeguimientoConvertor;
import ar.edu.um.ingenieria.convertor.TareaConvertor;
import ar.edu.um.ingenieria.convertor.UsuarioConvertor;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.dto.EtapaDTO;
import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;
import ar.edu.um.ingenieria.dto.TareaDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;
import ar.edu.um.ingenieria.service.impl.EtapaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.TareaServiceImpl;

@Service
public class SeguimientoManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioManager.class);
	
	@Autowired
	private EtapaServiceImpl etapaServiceImpl;
	
	@Autowired
	private TareaServiceImpl tareaServiceImpl;
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@Autowired
	private UsuarioConvertor usuarioConvertor;
	
	@Autowired
	private PlantaConvertor plantaConvertor;
	
	@Autowired
	private EstadoConvertor estadoConvertor;
	
	@Autowired
	private TareaConvertor tareaConvertor;
	
	@Autowired
	private SeguimientoConvertor seguimientoConvertor;
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	
	@Autowired
	private EtapaConvertor etapaConvertor;
	public void create(UsuarioDTO usuarioDTO, PlantaDTO plantaDTO, EstadoDTO estadoDTO) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
		Seguimiento seguimiento = new Seguimiento();
		if (estadoDTO.getId() == 1)
		{
		seguimiento.setEtapa(etapaServiceImpl.findById(1));
		seguimiento.setTarea(tareaServiceImpl.findById(1));
		seguimiento.setEstado(estadoConvertor.convertToEntity(estadoDTO));
		} else if (estadoDTO.getId() == 2){
			seguimiento.setEtapa(etapaServiceImpl.findById(6));
			seguimiento.setTarea(tareaServiceImpl.findById(7));
			seguimiento.setEstado(estadoConvertor.convertToEntity(estadoDTO));
		} else {
			seguimiento.setEtapa(etapaServiceImpl.findById(3));
			seguimiento.setEstado(estadoConvertor.convertToEntity(estadoDTO));
		}
		seguimiento.setUsuario(usuarioConvertor.convertToEntity(usuarioDTO));
		seguimiento.setPlanta(plantaConvertor.convertToEntity(plantaDTO));
		seguimiento.setFechaInicio(calendar.getTime());
		seguimiento.setUltimoRiego(calendar.getTime());
		calendar.add(Calendar.HOUR, seguimiento.getPlanta().getTiempoRiego().getHours());
		seguimiento.setProximoRiego(calendar.getTime());
		seguimientoServiceImpl.create(seguimiento);
	}
	
	public void update (UsuarioDTO usuarioDTO, PlantaDTO plantaDTO, EstadoDTO estadoDTO, TareaDTO tareaDTO, EtapaDTO etapaDTO,SeguimientoDTO seguimientoDTO)
	{
		Seguimiento nativeSeguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
		nativeSeguimiento.setUsuario(usuarioConvertor.convertToEntity(usuarioDTO));
		nativeSeguimiento.setTarea(tareaConvertor.convertToEntity(tareaDTO));
		nativeSeguimiento.setEtapa(etapaConvertor.convertToEntity(etapaDTO));
		nativeSeguimiento.setEstado(estadoConvertor.convertToEntity(estadoDTO));
		nativeSeguimiento.setPlanta(plantaConvertor.convertToEntity(plantaDTO));
		seguimientoServiceImpl.create(nativeSeguimiento);
	}
	
	public void regar (SeguimientoDTO seguimientoDTO) {
		Seguimiento seguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
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
	
	public void sueloPreparado (SeguimientoDTO seguimientoDTO) {
		Seguimiento seguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
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
	
	public void sembrado (SeguimientoDTO seguimientoDTO) {
		Seguimiento seguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
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
	
	public void transplantar (SeguimientoDTO seguimientoDTO) {
		Seguimiento seguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
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
	
	public void abonar (SeguimientoDTO seguimientoDTO) {
		Seguimiento seguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
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
	
	public void podar (SeguimientoDTO seguimientoDTO) {
		Seguimiento seguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
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
	
	public void cosechar (SeguimientoDTO seguimientoDTO) {
		Seguimiento seguimiento = seguimientoConvertor.convertToEntity(seguimientoDTO);
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
	
	public List<Seguimiento> findByUser(UsuarioDTO usuarioDTO)
	{
		return usuarioConvertor.convertToEntity(usuarioDTO).getSeguimiento();
	}

	public void delete(Integer id) {
		Seguimiento seguimiento = seguimientoServiceImpl.findById(id);		
		seguimientoServiceImpl.remove(seguimiento);
	}

}
