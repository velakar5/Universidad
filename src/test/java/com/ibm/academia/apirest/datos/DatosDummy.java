package com.ibm.academia.apirest.datos;

import com.ibm.academia.apirest.entities.Carrera;

public class DatosDummy {

    public static Carrera carreraD1(){
        return new Carrera(null, "Ingenieria en Sistemas", 50, 5);
    }
    public static Carrera carreraD2(){
        return new Carrera(null, "Licenciatura en Sistemas", 45, 4);
    }
    public static Carrera carreraD3(){
        return new Carrera(null, "Ingenieria Industrial", 60, 5);
    }
}
