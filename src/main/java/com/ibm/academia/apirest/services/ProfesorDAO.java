package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Primary;

public interface ProfesorDAO extends PersonaDAO {

	public Iterable<Persona> findProfesoresByCarrera(String carrera);

	public Persona actualizar(Persona profesorEncontrado, Persona profesor);

	public Persona asignaProfesorCarrera(Persona profesor, List<Carrera> carreras);

	public void asociarProfesorCarrera(Persona profesor, Carrera carrera);
}
