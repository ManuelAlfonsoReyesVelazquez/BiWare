package com.example.usuarios.service;

import java.util.List;

import com.example.usuarios.model.Usuario;

public interface IUsuarioService {

	Usuario crearUsuario(Usuario usuario);

	Usuario obtenerUsuarioPorID(Long id);

	List<Usuario> obtenerTodosLosUsuarios();

	Usuario actualizarUsuario(Usuario usuario);

	void eliminarUsuario(Long id);

}
