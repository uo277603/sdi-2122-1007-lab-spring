package com.uniovi.sdi21221007spring.controllers;

import com.uniovi.sdi21221007spring.entities.Profesor;
import com.uniovi.sdi21221007spring.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfesorController {

    @Autowired
    private ProfesorService teacherService;

    @RequestMapping("/profesor/add")
    public String setTeacher(@ModelAttribute Profesor teacher){
        teacherService.addProfesor(teacher);
        return teacher.toString();
    }

    @RequestMapping(value="/profesor/edit/{id}", method= RequestMethod.POST)
    public String getEdit(@ModelAttribute Profesor teacher, @PathVariable Long id){
        return "Editar: " + teacher.toString();
    }

    @RequestMapping("/profesor/details/{id}")
    public String getDetail(@PathVariable Long id){
        return teacherService.getProfesor(id).toString();
    }

    @RequestMapping("/profesor/delete/{id}")
    public String deleteTeacher(@PathVariable Long id){
        teacherService.deleteProfesor(id);
        return "Eliminado: " + id;
    }
}
