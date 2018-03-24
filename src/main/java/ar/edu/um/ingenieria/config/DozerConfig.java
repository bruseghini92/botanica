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
			mapping(Usuario.class, UsuarioDTO.class);
			mapping(Persona.class, PersonaDTO.class);
			mapping(Rol.class, RolDTO.class);
		}
	};
	BeanMappingBuilder plantaBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(Planta.class, PlantaDTO.class);
			mapping(Suelo.class, SueloDTO.class);
			mapping(Clima.class, ClimaDTO.class);
			mapping(Temporada.class, TemporadaDTO.class);
			mapping(TipoPlanta.class, TipoPlantaDTO.class);
		}
	};
	BeanMappingBuilder seguimientoBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(Seguimiento.class, SeguimientoDTO.class);
			mapping(Estado.class, EstadoDTO.class);
			mapping(Etapa.class, EtapaDTO.class);
			mapping(Tarea.class, TareaDTO.class);
		}
	};
	BeanMappingBuilder ventaBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(TipoVenta.class, TipoVentaDTO.class);
			mapping(Venta.class, VentaDTO.class);
		}
	};
	BeanMappingBuilder temaBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(Respuesta.class, RespuestaDTO.class);
			mapping(Categoria.class, CategoriaDTO.class);
			mapping(Tema.class, TemaDTO.class);
		}
	};
}
