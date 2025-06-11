package com.example.usuarios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre no puede ser nulo o vacío.")
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@NotBlank(message = "El email no puede ser nulo o vacío.")
	@Email(message = "El formato del email es inválido.")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@NotNull(message = "La edad no puede ser nula.")
	@Column(name = "edad", nullable = false)
	@Min(value = 1, message = "La edad debe ser al menos 1.")
	private String edad;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String email, String edad) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", email=");
		builder.append(email);
		builder.append(", edad=");
		builder.append(edad);
		builder.append("]");
		return builder.toString();
	}

}
