package com.uniovi.sdi21221007spring.services;

import com.uniovi.sdi21221007spring.entities.Mark;
import com.uniovi.sdi21221007spring.entities.Profesor;
import com.uniovi.sdi21221007spring.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    /*
    List<Profesor> profesores = new ArrayList<>();

    public ProfesorService(){
        profesores.add(new Profesor("1", "Daniel", "Ferreira", "teoria"));
        profesores.add(new Profesor("2", "Mario", "Gomez", "teoria"));
        profesores.add(new Profesor("3", "Antonio", "Garcia", "practicas"));
    }

     */

    public List<Profesor> getProfesors(){
        List<Profesor> profesors = new ArrayList<Profesor>();
        profesorRepository.findAll().forEach(profesors::add);
        return profesors;
    }

    public void addProfesor(Profesor profesor){
        profesorRepository.save(profesor);
    }

    public void deleteProfesor(Long id){
        profesorRepository.deleteById(id);

    }

    public Profesor getProfesor(Long id){
        return profesorRepository.findById(id).get();
    }
}
