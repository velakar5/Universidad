package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;

import java.util.Optional;

public class PersonaDAOImplements extends GenericoDAOImplements<Persona, PersonaRepository> implements PersonaDAO {

	public PersonaDAOImplements(PersonaRepository repository) {
		super(repository);
	}

	@Override
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
		return repository.buscarPorNombreYApellido(nombre, apellido);
	}

	@Override
	public Optional<Persona> buscarPorDni(String dni) {
		return repository.buscarPorDni(dni);
	}

	@Override
	public Optional<Persona> buscarPersonaApellido(String apellido) {
		return repository.buscarPersonaApellido(apellido);
	}
}
