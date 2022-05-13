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

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exeptions.NotFoundException;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

	@Autowired
	@Qualifier("empleadoDAOImplements")
	private PersonaDAO empleadoDao;
	
	/**
	 * Obtener todos los empleados
	 * 
	 * @NotFoundException En caso de que falle actualizando el objeto de empleado
	 * @return Retorna una lista de tipo empleado con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@GetMapping("/todos")
	public ResponseEntity<?> obtenerTodos(){
		List<Persona> empleados = (List<Persona>) empleadoDao.buscarTodos();
		if(empleados.isEmpty())
			throw new NotFoundException("No existen alumnos");
		return new ResponseEntity<List<Persona>>(empleados,HttpStatus.OK);
	}
	
	/**
	 * guardar un empleado
	 * 
	 * @param empleado   objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de alumno
	 * @return Retorna un objeto de tipo empleado con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PostMapping
	public ResponseEntity<?> creaEmpleado(@RequestBody Persona empleado){
		Persona empleadoGuardado = empleadoDao.guardar(empleado);
		return new ResponseEntity<Persona>(empleadoGuardado,HttpStatus.CREATED);
	}
	
	/**
	 * Actualizar un empleado
	 * 
	 * @param empleadoId Parametro para buscar la empleado
	 * @param empleado   objeto (json con la informacion a modificar)
	 * @NotFoundException En caso de que falle actualizando el objeto de empleado
	 * @return Retorna un objeto de tipo empleado con la info actualizada
	 * @Author Carlos Velazquez
	 */
	@PutMapping("/{empleadoId}")
	public ResponseEntity<?> actualizaEmpleado(@PathVariable Integer empleadoId, @RequestBody Persona empleado){
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		if(oEmpleado.isEmpty())
			throw new NotFoundException(String.format("El empleado con Id: %d no existe", empleadoId));
		Persona empleadoActualizado = ((EmpleadoDAO)empleadoDao).actualizarEmpleado(oEmpleado.get(), empleado);
		return new ResponseEntity<Persona>(empleadoActualizado,HttpStatus.OK);
	}
	
	/**
	 * eliminar un empleado
	 * 
	 * @param empleadoId Parametro para eliminar la aula
	 * @NotFoundException En caso de que falle eliminacion el objeto de empleado
	 * @return Retorna un mensaje con la confirmacion de la eliminacion
	 * @Author Carlos Velazquez
	 */
	@DeleteMapping("/{empleadoId}")
	public ResponseEntity<?> eliminaEmpleado(@PathVariable Integer empleadoId){
		Optional<Persona> oEmpleado = empleadoDao.buscarPorId(empleadoId);
		if(oEmpleado.isEmpty())
			throw new NotFoundException(String.format("El empleado con Id: %d no existe", empleadoId));
		empleadoDao.eliminarPorId(oEmpleado.get().getId());
		 return new ResponseEntity<String>("Empleado ID: " + empleadoId+ " se elimino satisfactoriamente", HttpStatus.OK);
	}
}
