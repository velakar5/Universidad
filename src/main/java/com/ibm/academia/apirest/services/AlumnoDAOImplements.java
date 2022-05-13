package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlumnoDAOImplements extends PersonaDAOImplements implements AlumnoDAO {

	@Autowired
	public AlumnoDAOImplements(@Qualifier("repositorioAlumnos") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre) {
		return ((AlumnoRepository) repository).buscarAlumnoPorNombreCarrera(nombre);
	}

	@Override
	@Transactional
	public Persona actualizar(Persona alumnoEncontrada, Persona alumno) {
		Persona alumnoAct = null;
		alumnoEncontrada.setNombre(alumno.getNombre());
		alumnoEncontrada.setApellido(alumno.getApellido());
		alumnoEncontrada.setDireccion(alumno.getDireccion());
		alumnoAct = repository.save(alumnoEncontrada);
		return alumnoAct;
	}

	@Override
	@Transactional
	public Persona asociarAlumnoCarrera(Persona alumno, Carrera carrera) {
		((Alumno) alumno).setCarrera(carrera);
		return repository.save(alumno);
	}
}
