package com.ibm.academia.apirest.services;

import java.util.List;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;

public interface PabellonDAO extends GenericoDAO<Pabellon> {

	public List<Pabellon> buscarPabellonesPorLocalidad(String pabellon);

	public List<Pabellon> buscarPorNombre(String nombre);

	public Pabellon actualizar(Pabellon pabellonEncontrado, Pabellon pabellon);
}
