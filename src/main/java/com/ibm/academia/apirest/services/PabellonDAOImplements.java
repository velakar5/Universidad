package com.ibm.academia.apirest.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.PabellonRepository;

@Service
public class PabellonDAOImplements extends GenericoDAOImplements<Pabellon, PabellonRepository> implements PabellonDAO {

	public PabellonDAOImplements(PabellonRepository repository) {
		super(repository);
	}

	@Override
	public List<Pabellon> buscarPabellonesPorLocalidad(String pabellon) {
		// TODO Auto-generated method stub
		return repository.buscarPorLocalidad(pabellon);
	}

	@Override
	public List<Pabellon> buscarPorNombre(String nombre) {
		return repository.buscarPorNombres(nombre);
	}
	
	@Override
	@Transactional
	public Pabellon actualizar(Pabellon pabellonEncontrado, Pabellon pabellon) {
		Pabellon pabellonActualizado = null;
		pabellonEncontrado.setNombre(pabellon.getNombre());
		pabellonEncontrado.setDireccion(pabellon.getDireccion());
		pabellonEncontrado.setTamanoMetrosCuadrados(pabellon.getTamanoMetrosCuadrados());
		pabellonActualizado = repository.save(pabellonEncontrado);
		return pabellonActualizado;
	}

}
