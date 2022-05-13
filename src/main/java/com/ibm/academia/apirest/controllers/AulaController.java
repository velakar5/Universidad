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
	
	@GetMapping("/porNombrePizarron/{pizarron}")
	public ResponseEntity<?> buscarPorNombrePizarron(@PathVariable String pizarron){
		List<Aula> aulas = aulaDao.buscarPorTipoPizarron(pizarron);
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas");
		return new ResponseEntity<List<Aula>>(aulas,HttpStatus.OK);
	}
	
	@GetMapping("/porNombrePabellon/{pabellon}")
	public ResponseEntity<?> buscarPorNombrePabellon(@PathVariable String pabellon){
		List<Aula> aulas = aulaDao.buscarPorNombrePabellon(pabellon);
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas");
		return new ResponseEntity<List<Aula>>(aulas,HttpStatus.OK);
	}
	
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerTodos() {
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	@GetMapping("/aulaId/{aulaId}")
	public ResponseEntity<?> obtenerAulaID(@PathVariable Integer aulaId) {
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		if (!oAula.isPresent())
			throw new NotFoundException(String.format("Aula con Id: %d no encontrado", aulaId));
		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}
	
	@PutMapping("update/aulaId/{aulaId}")
	public ResponseEntity<?> actualizarProfesor(@PathVariable Integer aulaId, @RequestBody Aula aula) {
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		if (!oAula.isPresent())
			throw new NotFoundException(String.format("Aula con Id: %d no encontrado", aulaId));
		Aula aulaActualizado = ((AulaDAO) aulaDao).actualizar(oAula.get(), aula);
		return new ResponseEntity<Aula>(aulaActualizado, HttpStatus.OK);
	}
	
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
