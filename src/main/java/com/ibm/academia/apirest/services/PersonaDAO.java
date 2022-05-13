package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;

import java.util.Optional;

public interface PersonaDAO extends GenericoDAO<Persona> {

	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);

	public Optional<Persona> buscarPorDni(String dni);

	public Optional<Persona> buscarPersonaApellido(String apellido);

}
