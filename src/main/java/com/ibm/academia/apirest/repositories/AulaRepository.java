package com.ibm.academia.apirest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Aula;

@Repository("repositrioAulas")
public interface AulaRepository extends CrudRepository<Aula, Integer> {

	@Query(value = "select * from aulas where tipo_pizarron like %?1%", nativeQuery = true)
	public List<Aula> buscarPorPizarron(String pizarron);

	@Query(value = "select * from aulas a inner join pabellones p on "
			+ "a.pabellon_id = p.id where p.nombres like %?1%", nativeQuery = true)
	public List<Aula> buscarPorNombrePabellon(String pabellon);

}
