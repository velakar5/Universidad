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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.services.PabellonDAO;

@RestController
@RequestMapping("/pabellon")
public class PabellonController {

	@Autowired
	@Qualifier("pabellonDAOImplements")
	private PabellonDAO pabellonDao;

	/**
	 * Obtener todos los pabellones por nombre
	 * 
	 * @NotFoundException En caso de que falle actualizando el objeto de pabellon
	 * @return Retorna una lista de tipo empleado con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/porNombres/{nombre}")
	public ResponseEntity<?> buscarPorNombrePAbellon(@PathVariable String nombre) {
		List<Pabellon> pabellones = pabellonDao.buscarPorNombre(nombre);
		if (pabellones.isEmpty())
			throw new NotFoundException("No existen pabellonres");
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}

	
	/**
	 * Obtener pabellones por localidad
	 * 
	 * @param localidadId Parametro para buscar la localidad
	 * @NotFoundException En caso de que falle actualizando el objeto de pabellon
	 * @return Retorna una lista de tipo pabellon con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("porLocalidad/{localidad}")
	public ResponseEntity<?> buscarPorLocalidad(@PathVariable String localidad) {
		List<Pabellon> pabellones = pabellonDao.buscarPabellonesPorLocalidad(localidad);
		if (pabellones.isEmpty())
			throw new NotFoundException("No existen pabellonres");
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	
	/**
	 * Obtener todos los pabellones
	 * 
	 * @NotFoundException En caso de que falle actualizando el objeto de pabellon
	 * @return Retorna una lista de tipo pabellon con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping
	public ResponseEntity<?> obtenerTodos(){
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}
	
	/**
	 * Obtener pabellon por Id
	 * 
	 * @param pabellonId Parametro para buscar la pabellon
	 * @NotFoundException En caso de que falle actualizando el objeto de pabellon
	 * @return Retorna una lista de tipo pabellon con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/pabellonId/{pabellonId}")
	public ResponseEntity<?> obtenerPabellonID(@PathVariable Integer pabellonId) {
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		if (!oPabellon.isPresent())
			throw new NotFoundException(String.format("Pabellon con Id: %d no encontrado", pabellonId));
		return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);
	}
	
	/**
	 * Actualizar un pabellon
	 * 
	 * @param pabellonId Parametro para buscar el pabellon
	 * @param pabellon   objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de pabellon
	 * @return Retorna un objeto de tipo empleado con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("update/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon) {
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		if (!oPabellon.isPresent())
			throw new NotFoundException(String.format("Pabellon con Id: %d no encontrado", pabellonId));
		Pabellon pabellonActualizado = ((PabellonDAO) pabellonDao).actualizar(oPabellon.get(),pabellon);
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK);
	}
	
	/**
	 * eliminar un pabellon
	 * 
	 * @param empleadoId Parametro para eliminar un pabellon
	 * @NotFoundException En caso de que falle eliminacion el objeto de empleado
	 * @return Retorna un mensaje con la confirmacion de la eliminacion
	 * @Author Carlos Velazquez
	 */
	@DeleteMapping("{pabellonId}")
	public ResponseEntity<?> eliminarPabellon(@PathVariable Integer pabellonId) {
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		if (!oPabellon.isPresent())
			throw new NotFoundException(String.format("Pabellon con Id: %d no encontrado", pabellonId));
		pabellonDao.eliminarPorId(pabellonId);
		return new ResponseEntity<String>("Pabellon ID: " + pabellonId + " se elimino satisfactoriamente",
				HttpStatus.OK);

	}

	
	
}
