package com.example.usuarios.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.usuarios.exception.DuplicateResourceException;
import com.example.usuarios.exception.ResourceNotFoundException;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;
import com.example.usuarios.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		try {
			return usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException ex) {
			if (ex.getMessage() != null && ex.getMessage().contains("ConstraintViolationException")
					|| ex.getMessage().contains("email")) {
				throw new DuplicateResourceException("El email '" + usuario.getEmail() + "' ya está registrado.");
			}
			throw ex;
		}

	}

	@Override
	public Usuario obtenerUsuarioPorID(Long id) {
		return filtraUsuarioPorId(id);
	}

	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		Usuario usuarioExistente = filtraUsuarioPorId(usuario.getId());
		usuarioExistente.setNombre(usuario.getNombre());
		usuarioExistente.setEmail(usuario.getEmail());
		usuarioExistente.setEdad(usuario.getEdad());
		return usuarioRepository.save(usuarioExistente);
	}

	@Override
	public void eliminarUsuario(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
		}
		usuarioRepository.deleteById(id);
	}

	private Usuario filtraUsuarioPorId(Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
		}
	}
	

}
