package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.services.AulaDAO;

@RestController
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private AulaDAO aulaDao;
	
	/**
	 * Obtener alumnos por pizarron
	 * 
	 * @param pizarron Parametro para buscar la aula
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna una lista de tipo alumno con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/porNombrePizarron/{pizarron}")
	public ResponseEntity<?> buscarPorNombrePizarron(@PathVariable String pizarron){
		List<Aula> aulas = aulaDao.buscarPorTipoPizarron(pizarron);
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas");
		return new ResponseEntity<List<Aula>>(aulas,HttpStatus.OK);
	}
	
	/**
	 * Obtener alumnos por pabellon
	 * 
	 * @param pabellon Parametro para buscar la aula
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna una lista de tipo alumno con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/porNombrePabellon/{pabellon}")
	public ResponseEntity<?> buscarPorNombrePabellon(@PathVariable String pabellon){
		List<Aula> aulas = aulaDao.buscarPorNombrePabellon(pabellon);
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas");
		return new ResponseEntity<List<Aula>>(aulas,HttpStatus.OK);
	}
	
	/**
	 * Obtener todos las aulas
	 * 
	 * @NotFoundException En caso de que falle actualizando el objeto de aula
	 * @return Retorna una lista de tipo aula con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerTodos() {
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	/**
	 * Obtener aula por Id
	 * 
	 * @param aulaId Parametro para buscar la aula
	 * @NotFoundException En caso de que falle actualizando el objeto de aula
	 * @return Retorna una lista de tipo aula con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/aulaId/{aulaId}")
	public ResponseEntity<?> obtenerAulaID(@PathVariable Integer aulaId) {
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		if (!oAula.isPresent())
			throw new NotFoundException(String.format("Aula con Id: %d no encontrado", aulaId));
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
	/**
	 * Actualizar una aula
	 * 
	 * @param aulaId Parametro para buscar la alumno
	 * @param aula   objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de aula
	 * @return Retorna un objeto de tipo aula con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("update/aulaId/{aulaId}")
	public ResponseEntity<?> actualizarProfesor(@PathVariable Integer aulaId, @RequestBody Aula aula) {
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		if (!oAula.isPresent())
			throw new NotFoundException(String.format("Aula con Id: %d no encontrado", aulaId));
		Aula aulaActualizado = ((AulaDAO) aulaDao).actualizar(oAula.get(), aula);
		return new ResponseEntity<Aula>(aulaActualizado, HttpStatus.OK);
	}
	
	/**
	 * eliminar un aula
	 * 
	 * @param aulaId Parametro para eliminar la aula
	 * @NotFoundException En caso de que falle actualizando el objeto de aula
	 * @return Retorna un mensaje con la confirmacion de la eliminacion
	 * @Author Carlos Velazquez
	 */
	@DeleteMapping("{aulaId}")
	public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId) {
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		if (!oAula.isPresent())
			throw new NotFoundException(String.format("Aula con Id: %d no encontrado", aulaId));
		aulaDao.eliminarPorId(aulaId);
		return new ResponseEntity<String>("Aula ID: " + aulaId + " se elimino satisfactoriamente",
				HttpStatus.OK);

	}
}
