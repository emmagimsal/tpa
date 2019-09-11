package com.tp.db.jdbc.dto;

public class Usuario {

	private String nombre;
	private String apellido;
	private String cdUsuario;
	private String email;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public Usuario(String nombre, String apellido, String cdUsuario,
			String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cdUsuario = cdUsuario;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido
				+ ", cdUsuario=" + cdUsuario + ", email=" + email + "]";
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
