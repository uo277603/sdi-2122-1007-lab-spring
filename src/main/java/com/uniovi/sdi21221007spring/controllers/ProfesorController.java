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

    @RequestMapping(value="/profesor/edit/{dni}", method= RequestMethod.POST)
    public String getEdit(@ModelAttribute Profesor teacher, @PathVariable String dni){
        return "Editar: " + teacher.toString();
    }

    @RequestMapping("/profesor/details/{dni}")
    public String getDetail(@PathVariable String dni){
        return "Eliminar: " + teacherService.getProfesor(dni).toString();
    }

    @RequestMapping("/profesor/delete/{dni}")
    public String deleteTeacher(@PathVariable String dni){
        teacherService.deleteProfesor(dni);
        return "Eliminado: " + dni;
    }
}
