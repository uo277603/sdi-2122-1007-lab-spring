package com.uniovi.sdi21221007spring.services;

import com.uniovi.sdi21221007spring.entities.Profesor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorService {

    List<Profesor> profesores = new ArrayList<>();

    public ProfesorService(){
        profesores.add(new Profesor("1", "Daniel", "Ferreira", "teoria"));
        profesores.add(new Profesor("2", "Mario", "Gomez", "teoria"));
        profesores.add(new Profesor("3", "Antonio", "Garcia", "practicas"));
    }

    public List<Profesor> getProfesors(){
        return new ArrayList<>(profesores);
    }

    public void addProfesor(Profesor profesor){
        profesores.add(profesor);
    }

    public void deleteProfesor(String dni){
        profesores.removeIf(profesor -> profesor.getDni().equals(dni));

    }

    public Profesor getProfesor(String dni){
        for (Profesor t: profesores
        ) {
            if(t.getDni().equals(dni))
                return t;
        }
        return new Profesor();
    }
}
