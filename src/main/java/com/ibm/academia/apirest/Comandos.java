package com.ibm.academia.apirest;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ibm.academia.apirest.enums.TipoEmpleado.ADMINISTRATIVO;

@Component
public class Comandos implements CommandLineRunner {

    //@Autowired
    //private CarreraDAO carreraDAO;

    @Autowired
    @Qualifier("profesorDAOImplements")
    private PersonaDAO personaDAO;

    //@Autowired
    //@Qualifier("empleadoDAOImplements")
    //private PersonaDAO personaDAO;

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private ProfesorDAO profesorDAO;


    @Override
    public void run(String... args) throws Exception {

        /*Carrera finanzas = new Carrera(null, "Ingenieria en Finanzas", 20, 3);
        Carrera carrraGuardada = carreraDAO.guardar(finanzas);
        System.out.println(carrraGuardada.toString()); */

        //Lo que llego de tipo carrera me lo combiarta a un objeto de Carrera

        //------------Buscar Carrera

        /*Carrera carrera =null;
        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(1);
        if(oCarrera.isPresent()){
            carrera = oCarrera.get();
            System.out.println(carrera.toString());
        }
        else{
            System.out.println("Carrera no encontrada");
        }
        //----------------Modificar carrera
        carrera.setCantidadAnios(7);
        carrera.setCantidadMaterias(60);
        carreraDAO.guardar(carrera);
        System.out.println(carreraDAO.buscarPorId(1).orElse(new Carrera()).toString());
        //------------------Eliminar
        //carreraDAO.eliminarPorId(2);
        //System.out.println(carreraDAO.buscarPorId(2).orElse(new Carrera()).toString());
       // System.out.println(oCarrera.toString());
         */

        //Inserciones de carreras
        /*Carrera ingSistemas = new Carrera(null, "Ingenieria en Sistemas", 60, 5);
        Carrera ingIndustrial = new Carrera(null, "Ingenieria Industrial", 55, 5);
        Carrera ingAlimentos = new Carrera(null, "Ingenieria en Alimentos", 53, 5);
        Carrera ingElectronica = new Carrera(null, "Ingenieria en Electronica", 45, 5);
        Carrera licSistemas = new Carrera(null, "Licenciatura en Sistemas", 40, 4);
        Carrera licTurismo = new Carrera(null, "Licenciatura en Turismo", 42, 4);
        Carrera licYoga = new Carrera(null, "Licenciatura en Yoga", 25,3);
        Carrera licRecursos = new Carrera(null, "Licenciatura en Recursos", 33,3);
        carreraDAO.guardar(ingSistemas);
        carreraDAO.guardar(ingIndustrial);
        carreraDAO.guardar(ingAlimentos);
        carreraDAO.guardar(ingElectronica);
        carreraDAO.guardar(licSistemas);
        carreraDAO.guardar(licTurismo);
        carreraDAO.guardar(licYoga);
        carreraDAO.guardar(licRecursos);
         */
        //Consultas de carrera
        /*Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);
        Iterable<Persona> alumnos = personaDAO.buscarTodos();
        alumnos.forEach(alumno -> ((Alumno)alumno).setCarrera(ingSistemas.get()) );
        alumnos.forEach(alumno -> personaDAO.guardar(alumno));
         */

        //Optional<Carrera> ingSistemas = carreraDAO.buscarPorId(1);
        //Iterable<Persona> alumnosCarrera = ((AlumoDAO) personaDAO).buscarAlumnoPorNombreCarrera(ingSistemas.get().getNombre());
        //alumnosCarrera.forEach(System.out::println);

        /*List<Carrera> carreras = (List<Carrera>) carreraDAO.findCarrerasByNombreContains("Sistemas");
        carreras.forEach(System.out::println);*/

        //List<Carrera> carrerasIgnireCase1 = (List<Carrera>) carreraDAO.findCarrerasByNombreContainsIgnoreCase("SISTEMAS");
        //List<Carrera> carrerasIgnireCase2 = (List<Carrera>) carreraDAO.findCarrerasByNombreContainsIgnoreCase("sistemas");
        //carrerasIgnireCase1.forEach(System.out::println);
        //carrerasIgnireCase2.forEach(System.out::println);

        //List<Carrera> carrerasPorAnio = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(3);
        //carrerasPorAnio.forEach(System.out::println);


        //Iterable<Persona> profesoresCarrera = ((ProfesorDAO)personaDAO).findProfesoresByCarrera("Ingenieria en Sistemas");
        //profesoresCarrera.forEach(System.out::println);

        //-----Profeores------
        Iterable<Persona> profesoresCarrera = profesorDAO.findProfesoresByCarrera("Ingenieria Industrial");
        profesoresCarrera.forEach(System.out::println);


        //-------Empleado-------
        //Iterable<Persona> empleadoTipo = empleadoDAO.findEmpleadoByTipoEmpleado(ADMINISTRATIVO);
        //empleadoTipo.forEach(System.out::println);



    }
}
