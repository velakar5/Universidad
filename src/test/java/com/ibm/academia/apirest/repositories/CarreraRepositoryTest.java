package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.entities.Carrera;
import static org.assertj.core.api.Assertions.assertThat;

import com.ibm.academia.apirest.services.CarreraDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CarreraRepositoryTest {

    @Autowired
    private CarreraRepository carreraRepository;


    @Test
    @DisplayName("Test: Busca carreras por nombre")
    void findCarrerasByNombreContains(){
        //Given
        carreraRepository.save(DatosDummy.carreraD1());
        carreraRepository.save(DatosDummy.carreraD2());
        carreraRepository.save(DatosDummy.carreraD3());

        //When
        Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContains("Sistemas");

        //Then
        assertThat(((List<Carrera>)expected).size() == 2).isTrue();

    }


    @Test
    @Disabled
    void findCarrerasByNombreContainsIgnoreCase(){

    }
    @Test
    @Disabled
    void findCarrerasByCantidadAniosAfter(){

    }
}
