package ar.edu.um.ingenieria.config;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.edu.um.ingenieria.domain.Rol;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.RolDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;

@Configuration
public class DozerConfig {
	
	@Bean
	public DozerBeanMapper mapper() throws Exception {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(usuarioBuilder);
		//mapper.addMapping(plantaBuilder);
		return mapper;
	}
	
	
	BeanMappingBuilder usuarioBuilder = new BeanMappingBuilder() {
		@Override
		protected void configure() {
			mapping(Usuario.class, UsuarioDTO.class)
			.fields("user", "user")
			.fields("email", "email")
			.fields("password", "password")
			.fields("persona.nombre", "nombre")
			.fields("persona.apellido", "apellido")
			.fields("persona.edad", "edad")
			.fields("rol", "rol");
			mapping(Rol.class, RolDTO.class)
			.fields("id", "id")
			.fields("rol", "rol")
			.fields("descripcion", "descripcion");
		}
	};	
}
