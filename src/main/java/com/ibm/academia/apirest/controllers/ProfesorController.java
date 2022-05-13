package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

	@Autowired
	@Qualifier("profesorDAOImplements")
	private PersonaDAO profesorDao;

	@Autowired
	private CarreraDAO carreraDAO;

	@PostMapping
	public ResponseEntity<?> crearProfesor(@RequestBody Profesor profesor) {
		Persona profesorGuardado = profesorDao.guardar(profesor);
		return new ResponseEntity<Persona>(profesorGuardado, HttpStatus.CREATED);
	}

	/**
	 * Obtener todos los profesores
	 * 
	 * @NotFoundException En caso de que falle actualizando el objeto de profesro
	 * @return Retorna una lista de tipo profesor con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerTodos() {
		List<Persona> profesores = (List<Persona>) profesorDao.buscarTodos();
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}

	/**
	 * Obtener profesor por Id
	 * 
	 * @param profesorId Parametro para buscar el profesor
	 * @NotFoundException En caso de que falle actualizando el objeto de profesor
	 * @return Retorna una lista de tipo profesor con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/profesorId/{profesorId}")
	public ResponseEntity<?> obtenerProfesorID(@PathVariable Integer profesorId) {
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		if (!oProfesor.isPresent())
			throw new NotFoundException(String.format("Profesor con Id: %d no encontrado", profesorId));
		return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);
	}

	/**
	 * Actualizar un profesor
	 * 
	 * @param profesorId Parametro para buscar la profesor
	 * @param alumno   objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de profesor
	 * @return Retorna un objeto de tipo profesor con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("update/profesorId/{profesorId}")
	public ResponseEntity<?> actualizarProfesor(@PathVariable Integer profesorId, @RequestBody Persona profesor) {
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		if (!oProfesor.isPresent())
			throw new NotFoundException(String.format("Profesor con Id: %d no encontrado", profesorId));
		Persona profesorActualizado = ((ProfesorDAO) profesorDao).actualizar(oProfesor.get(), profesor);
		return new ResponseEntity<Persona>(profesorActualizado, HttpStatus.OK);
	}

	/**
	 * eliminar un profesor
	 * 
	 * @param profesorId Parametro para eliminar el profesor
	 * @NotFoundException En caso de que falle eliminacion el objeto de profesor
	 * @return Retorna un mensaje con la confirmacion de la eliminacion
	 * @Author Carlos Velazquez
	 */
	@DeleteMapping("{profesorId}")
	public ResponseEntity<?> eliminarProfesor(@PathVariable Integer profesorId) {
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		if (!oProfesor.isPresent())
			throw new NotFoundException(String.format("Profesor con Id: %d no encontrado", profesorId));
		profesorDao.eliminarPorId(profesorId);
		return new ResponseEntity<String>("Profesor ID: " + profesorId + " se elimino satisfactoriamente",
				HttpStatus.OK);

	}

	/**
	 * Asignar una carrera a un profesor
	 * 
	 * @param profesorId Parametro para buscar el profesor
	 * @param carreraId   Parametro para buscar la carrera
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera o profesor
	 * @return Retorna un objeto de tipo profesor con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("/profesorId/{profesorId}/carrera/{carreraId}")
	public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer profesorId, @PathVariable Integer carreraId) {
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);

		if (!oProfesor.isPresent()) {
			throw new NotFoundException(String.format("El profesor con Id: %d no existe", profesorId));
		}
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
		if (!oCarrera.isPresent()) {
			throw new NotFoundException(String.format("LA carrera con Id: %d no existe", carreraId));
		}

		((ProfesorDAO) profesorDao).asociarProfesorCarrera(oProfesor.get(), oCarrera.get());

		return new ResponseEntity<String>("actualizado", HttpStatus.OK);
	}
}
