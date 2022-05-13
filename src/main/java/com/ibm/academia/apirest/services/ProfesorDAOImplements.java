package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesorDAOImplements extends PersonaDAOImplements implements ProfesorDAO {

	@Autowired
	public ProfesorDAOImplements(@Qualifier("repositorioProfesores") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findProfesoresByCarrera(String carrera) {
		return ((ProfesorRepository) repository).findProfesoresByCarrera(carrera);
	}

	@Override
	@Transactional
	public Persona actualizar(Persona profesorEncontrado, Persona profesor) {
		Persona profesorActualizado = null;
		profesorEncontrado.setNombre(profesor.getNombre());
		profesorEncontrado.setApellido(profesor.getApellido());
		profesorEncontrado.setDireccion(profesor.getDireccion());
		profesorActualizado = repository.save(profesorEncontrado);
		return profesorActualizado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Persona asignaProfesorCarrera(Persona profesor, List<Carrera> carreras) {
		((Profesor) profesor).setCarreras((Set<Carrera>) carreras);
		return repository.save(profesor);
	}

	@Override
	@Transactional
	public void asociarProfesorCarrera(Persona profesor, Carrera carrera) {
		Set<Carrera> carreras = new HashSet<>();
		carreras.add(carrera);
		((Profesor) profesor).setCarreras(carreras);
		// return repository.save(profesor);
	}
}
