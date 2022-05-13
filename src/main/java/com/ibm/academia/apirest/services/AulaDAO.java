package com.ibm.academia.apirest.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.entities.Aula;

public interface AulaDAO extends GenericoDAO<Aula> {

	public List<Aula> buscarPorTipoPizarron(String pizarron);

	public List<Aula> buscarPorNombrePabellon(String nombre);
	
	public Aula actualizar(Aula aulaEncontrado, Aula aula);
}
