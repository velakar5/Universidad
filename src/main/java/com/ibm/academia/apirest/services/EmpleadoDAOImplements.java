package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoDAOImplements extends PersonaDAOImplements implements EmpleadoDAO {

	public EmpleadoDAOImplements(@Qualifier("repositorioEmpleados") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
		return ((EmpleadoRepository) repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
	}

	@Override
	@Transactional
	public Persona actualizarEmpleado(Persona empleadoEncontrado, Persona empleado) {
		Persona empleadoActualizado = null;
		empleadoEncontrado.setNombre(empleado.getNombre());
		empleadoEncontrado.setApellido(empleado.getApellido());
		empleadoEncontrado.setDireccion(empleado.getDireccion());
		empleadoActualizado = repository.save(empleadoEncontrado);
		return empleadoActualizado;
	}
}
