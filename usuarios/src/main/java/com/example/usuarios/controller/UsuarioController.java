package com.example.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.service.IUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping("/crearUsuario")
	public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
		return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
	}

	@GetMapping("/obtenerTodosLosUsuarios")
	public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
		List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@GetMapping("obtenerUsuarioPorId")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@Valid @RequestParam Long id) {
		return new ResponseEntity<>(usuarioService.obtenerUsuarioPorID(id), HttpStatus.OK);
	}

	@PutMapping("actualizarUsuario")
	public ResponseEntity<Usuario> actualizarUsuario(@Valid @RequestBody Usuario usuarioDetalles) {
		Usuario usuarioActualizado = usuarioService.actualizarUsuario(usuarioDetalles);
		return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
	}

	@DeleteMapping("eliminarUsuario")
	public ResponseEntity<HttpStatus> eliminarUsuario(@Valid @RequestParam Long id) {
		usuarioService.eliminarUsuario(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
