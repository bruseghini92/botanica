package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Usuario;


@Service
public class UsuarioServiceImpl extends ServiceImpl<Usuario, Integer>{

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;	
	
	@Autowired
	private PersonaServiceImpl personaServiceImpl;
	
	@Override
	public Usuario create(Usuario entity) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(hashedPassword);
		return super.create(entity);
	}

	@Override
	public void remove(Usuario entity) {
		
		super.remove(entity);
	}

	@Override
	public Usuario update(Usuario entity) {
		
		return super.update(entity);
	}

	@Override
	public Usuario findById(Integer id) {
	
		return super.findById(id);
	}

	@Override
	public List<Usuario> findAll() {
		return super.findAll();
	}
	
	public void create(Persona persona, Usuario usuario) {
		usuarioServiceImpl.create(usuario);
		persona.setUsuario(usuario);
		personaServiceImpl.create(persona);	
	}
	
	public void delete(Integer id) {
		Usuario usuario = usuarioServiceImpl.findById(id);
		personaServiceImpl.remove(usuario.getPersona());		
		usuarioServiceImpl.remove(usuario);
	}
	
}
