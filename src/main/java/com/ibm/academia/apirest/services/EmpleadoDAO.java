package com.ibm.academia.apirest.services;

import java.util.Optional;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO {
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

	public Persona actualizarEmpleado(Persona empleadoEncontrado, Persona empleado);
}
