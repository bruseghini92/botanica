package ar.edu.um.ingenieria.config;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.um.ingenieria.domain.*;
import ar.edu.um.ingenieria.dto.*;

@Configuration
public class DozerConfig {
	
	@Bean
	public DozerBeanMapper mapper() throws Exception {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(usuarioBuilder);
		mapper.addMapping(plantaBuilder);
		mapper.addMapping(seguimientoBuilder);
		mapper.addMapping(temaBuilder);
		return mapper;
	}
	
	
	BeanMappingBuilder usuarioBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(Usuario.class, UsuarioDTO.class)
			.fields("user", "user")
			.fields("email", "email")
			.fields("password", "password")
			.fields("persona", "persona")
			.fields("rol", "rol");
			mapping(Persona.class, PersonaDTO.class)
			.fields("id", "id")
			.fields("apellido", "apellido")
			.fields("nombre", "nombre")
			.fields("fechaNacimiento", "fechaNacimiento")
			.fields("usuario", "usuario");
			mapping(Rol.class, RolDTO.class)
			.fields("id", "id")
			.fields("rol", "rol")
			.fields("descripcion", "descripcion");
		}
	};
	BeanMappingBuilder plantaBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping (Planta.class,PlantaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("tipo","tipo")
			.fields("temporada","temporada")
			.fields("suelo","suelo")
			.fields("clima","clima")
			.fields("tiempoRiego","tiempoRiego")
			.fields("seguimiento","seguimiento");
			mapping(Suelo.class, SueloDTO.class)
			.fields("id", "id")
			.fields("nombre", "nombre")
			.fields("descripcion", "descripcion")
			.fields("plantas","plantas");
			mapping (Clima.class,ClimaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("plantas","plantas");
			mapping (Temporada.class,TemporadaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("fechaInicio","fechaInicio")
			.fields("fechaFin","fechaFin")
			.fields("plantas","plantas");
			mapping (TipoPlanta.class,TipoPlantaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("plantas","plantas");
		}
	};
	BeanMappingBuilder seguimientoBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(Seguimiento.class, SeguimientoDTO.class)
			.fields("id","id")
			.fields("usuario","usuario")
			.fields("planta","planta")
			.fields("estado","estado")
			.fields("etapa","etapa")
			.fields("tarea","tarea")
			.fields("ultimoRiego","ultimoRiego")
			.fields("proximoRiego","proximoRiego")
			.fields("fechaInicio","fechaInicio")
			.fields("fechaAbono","fechaAbono")
			.fields("fechaPoda","fechaPoda")
			.fields("fechaCosecha","fechaCosecha");
			mapping (Etapa.class,EtapaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("estado","estado")
			.fields("tareas","tareas");
			mapping (Estado.class,EstadoDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("seguimientos","seguimientos")
			.fields("etapas","etapas");
			mapping (Tarea.class,TareaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("etapa","etapa")
			.fields("seguimiento","seguimiento");
		}
	};
	BeanMappingBuilder ventaBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping (TipoVenta.class,TipoVentaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("ventas","ventas");
			mapping (Venta.class,VentaDTO.class)
			.fields("id","id")
			.fields("producto","producto")
			.fields("descripcion","descripcion")
			.fields("tipo_venta_id","tipo_venta_id")
			.fields("usuario","usuario")
			.fields("cerrado","cerrado")
			.fields("fecha","fecha");
		}
	};
	BeanMappingBuilder temaBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping (Respuesta.class, RespuestaDTO.class)
			.fields("id","id")
			.fields("tema","tema")
			.fields("texto","texto")
			.fields("usuario","usuario")
			.fields("fecha","fecha");	
			mapping (Categoria.class,CategoriaDTO.class)
			.fields("id","id")
			.fields("nombre","nombre")
			.fields("descripcion","descripcion")
			.fields("temas","temas");
			mapping (Tema.class,TemaDTO.class)
			.fields("id","id")
			.fields("titulo","titulo")
			.fields("usuario","usuario")
			.fields("cerrado","cerrado")
			.fields("texto","texto")
			.fields("categoria","categoria")
			.fields("respuestas","respuestas")
			.fields("fecha","fecha");		
		}
	};
}

