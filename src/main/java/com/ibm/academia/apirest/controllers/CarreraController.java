package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.exeptions.BadRequestExeption;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.services.CarreraDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

	@Autowired
	private CarreraDAO carreraDAO;


	/**
	 * Obtener todos las carreras
	 * 
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera
	 * @return Retorna una lista de tipo carrera con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/lista/carreras")
	public List<Carrera> buscarTodos() {

		List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();

		if (carreras.isEmpty()) {
			throw new BadRequestExeption("No existen carreras");
		}
		return carreras;

	}

	/**
	 * Obtener carrera por Id
	 * 
	 * @param carreraId Parametro para buscar la carrera
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera
	 * @return Retorna una lista de tipo carrera con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/id/{carreraId}")
	public Carrera buscarCarreraPorId(@PathVariable Integer carreraId) {

		Carrera carrera = carreraDAO.buscarPorId(carreraId).orElse(null);
		if (carrera == null) {
			throw new BadRequestExeption(String.format("La carrera con Id: &d no existe", carreraId));

		}
		return carrera;
	}

	/**
	 * guardar una carrera
	 * 
	 * @param carrera Objeto para guardar la carrera
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera
	 * @return Retorna un objeto de tipo carrera con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PostMapping
	public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result) {

		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrorres = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista de Errores", listaErrorres);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);

		}
		Carrera carreraGuardada = carreraDAO.guardar(carrera);

		return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
	}

	

	/**
	 * Actualizar una carrera
	 * 
	 * @param carreraId Parametro para buscar la alumno
	 * @param carrera objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera
	 * @return Retorna un objeto de tipo carrera con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("/update/carreraId/{carreraId}")
	public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera) {
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
		if (!oCarrera.isPresent()) {
			throw new NotFoundException(String.format("La carrera con Id: %d no existe", carreraId));
		}
		Carrera carreraActulizar = carreraDAO.actualizar(oCarrera.get(), carrera);
		return new ResponseEntity<Carrera>(carreraActulizar, HttpStatus.OK);
	}

	/**
	 * eliminar un carrera
	 * 
	 * @param carreraId Parametro para eliminar la aula
	 * @NotFoundException En caso de que falle eliminacion el objeto de alumno
	 * @return Retorna un mensaje con la confirmacion de la eliminacion
	 * @Author Carlos Velazquez
	 */
	@DeleteMapping("/carreraId/{carreraId}")
	public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
		if (!oCarrera.isPresent()) {
			throw new NotFoundException(String.format("La carrera con Id: %d no existe", carreraId));
		}
		carreraDAO.eliminarPorId(carreraId);
		respuesta.put("Ok", "Carrera Id: " + carreraId + " Eliminada exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);

	}
	
	/**
	 * Obtener carrera por nombre y apellido
	 * 
	 * @param aulaId Parametro para buscar la carrera
	 * @NotFoundException En caso de que falle actualizando el objeto de carrera
	 * @return Retorna una lista de tipo carrera con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/nombre/{nombre}/apellido/{apellido}")
	public List<Carrera> buscarCarrerasPorProfesorNombreYApellido(@PathVariable String nombre, @PathVariable String apellido) {

		List<Carrera> carrera = (List<Carrera>) carreraDAO.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
		if (carrera.isEmpty()) {
			throw new BadRequestExeption(String.format("La carrera con Id: &d no existe"));

		}
		return carrera;
	}

}
