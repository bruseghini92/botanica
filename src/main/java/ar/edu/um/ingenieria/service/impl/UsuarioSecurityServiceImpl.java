package ar.edu.um.ingenieria.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.repository.UsuarioRepository;

@Service
public class UsuarioSecurityServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Usuario> usuarioList = new ArrayList<Usuario>();

		usuarioList = usuarioRepository.findUsernames(username);
		if (usuarioList.size() > 0) {
			return usuarioList.get(0);
		} else {
			return null;
		}
	}
	
	public Integer GetIdUser () {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		Usuario usuario = usuarioRepository.findUsername(userName);
		return usuario.getId();
	}

}
