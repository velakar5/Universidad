package com.ibm.academia.apirest;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlumnosComandos implements CommandLineRunner {

    @Autowired
    private CarreraDAO carreraDAO;

    @Autowired
    @Qualifier("alumnoDAOImplements")
    private PersonaDAO personaDAO;

    @Autowired
    private AlumnoDAO alumnoDAO;

    @Override
    public void run(String... args) throws Exception {


        //Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);

        //Iterable<Persona> alumnos = personaDAO.buscarTodos();
        //alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(ingSistemas.get()) );
        //alumnos.forEach(alumno -> personaDAO.guardar(alumno));

        //Iterable es que es una lista
        //Optional, siemre se usa cuando se retorna un objeto
        //Optional<Persona> alumno = alumoDAO.buscarPorId(1);

        //Optional<Persona> personaDni = personaDAO.buscarPorDni(alumno.get().getDni());
        //System.out.println("DNI: "+ personaDni.toString());

        //Iterable<Persona> personaApellido = personaDAO.buscarPersonaApellido(alumno.get().getApellido());


        //Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);
        //Iterable<Persona> alumnosCarrera = alumnoDAO.buscarAlumnoPorNombreCarrera(ingSistemas.get().getNombre());
        //alumnosCarrera.forEach(System.out::println);
    }
}
