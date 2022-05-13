package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	@Qualifier("alumnoDAOImplements")
	private PersonaDAO alumnoDao;

	@Autowired
	private CarreraDAO carreraDAO;

	@PostMapping
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno) {

		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);

	}

	/**
	 * Obtener todos los alumnos
	 * 
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna una lista de tipo alumno con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerTodos() {
		List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
		if (alumnos.isEmpty()) {
			throw new NotFoundException("No existen alumnos");

		}
		return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	}


	/**
	 * Obtener alumnos por Id
	 * 
	 * @param aulaId Parametro para buscar la aula
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna una lista de tipo alumno con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> obtenerAlumnoId(@PathVariable Integer alumnoId) {

		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if (!oAlumno.isPresent()) {
			throw new NotFoundException(String.format("Alumno con Id: %d no encontrado", alumnoId));

		}

		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}

	/**
	 * Actualizar un alumno
	 * 
	 * @param alumnoId Parametro para buscar la alumno
	 * @param alumno   objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna un objeto de tipo alumno con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("/update/alumnoId/{alumnoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno) {
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if (!oAlumno.isPresent()) {
			throw new NotFoundException(String.format("El alumno con Id: %d no existe", alumnoId));
		}
		Persona alumnoActualizado = ((AlumnoDAO) alumnoDao).actualizar(oAlumno.get(), alumno);
		return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
	}

	/**
	 * eliminar un alumno
	 * 
	 * @param alumnoId Parametro para eliminar la aula
	 * @NotFoundException En caso de que falle eliminacion el objeto de alumno
	 * @return Retorna un mensaje con la confirmacion de la eliminacion
	 * @Author Carlos Velazquez
	 */
	@DeleteMapping("/alumnoId/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId) {

		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);

		if (!oAlumno.isPresent()) {
			throw new NotFoundException(String.format("El alumno con Id: %d no existe", alumnoId));
		}
		alumnoDao.eliminarPorId(oAlumno.get().getId());
		return new ResponseEntity<String>("Alumno ID: " + alumnoId + " se elimino satisfactoriamente", HttpStatus.OK);
	}

	/**
	 * Asignar una carrera a un alumno
	 * 
	 * @param alumnoId Parametro para buscar el alumno
	 * @param carreraId   Parametro para buscar la carrera
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera o alumno
	 * @return Retorna un objeto de tipo alumno con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("/alumnoId/{alumnoId}/carrera/{carreraId}")
	public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer alumnoId, @PathVariable Integer carreraId) {
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);

		if (!oAlumno.isPresent()) {
			throw new NotFoundException(String.format("El alumno con Id: %d no existe", alumnoId));
		}
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
		if (!oCarrera.isPresent()) {
			throw new NotFoundException(String.format("LA carrera con Id: %d no existe", carreraId));
		}

		Persona alumno = ((AlumnoDAO) alumnoDao).asociarAlumnoCarrera(oAlumno.get(), oCarrera.get());

		return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
	}

}
