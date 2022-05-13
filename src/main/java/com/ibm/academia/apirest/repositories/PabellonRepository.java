package com.ibm.academia.apirest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Pabellon;

@Repository("repositorioPAbellones")
public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {

	@Query("select p from Pabellon p where p.nombre like %?1%")
	public List<Pabellon> buscarPorNombres(String nombre);

	@Query(value = "select * from pabellones where localidad like %?1%", nativeQuery = true)
	public List<Pabellon> buscarPorLocalidad(String nombre);

}
