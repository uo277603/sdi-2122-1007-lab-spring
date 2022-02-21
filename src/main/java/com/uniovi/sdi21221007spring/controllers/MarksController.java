package com.uniovi.sdi21221007spring.controllers;

import com.uniovi.sdi21221007spring.entities.Mark;
import com.uniovi.sdi21221007spring.services.MarksService;
import com.uniovi.sdi21221007spring.services.UsersService;
import com.uniovi.sdi21221007spring.validators.AddMarkFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MarksController {

    @Autowired
    private MarksService marksService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AddMarkFormValidator addMarkFormValidator;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/mark/list")
    public String getList(Model model) {
        Set<Mark> consultedList= (Set<Mark>) httpSession.getAttribute("consultedList");
        if ( consultedList == null ) {
            consultedList = new HashSet<Mark>();
        }
        model.addAttribute("consultedList", consultedList);
        model.addAttribute("markList", marksService.getMarks());
        return "mark/list";
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@Validated Mark mark, BindingResult result, Model model) {
        addMarkFormValidator.validate(mark, result);
        if(result.hasErrors()) {
            model.addAttribute("usersList", usersService.getUsers());
            return "/mark/add";
        }

        marksService.addMark(mark);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/add")
    public String getMark(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        model.addAttribute("mark", new Mark());
        return "mark/add";
    }


    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers());
        return "mark/edit";
    }

    @RequestMapping(value="/mark/edit/{id}", method=RequestMethod.POST)
    public String setEdit(@ModelAttribute("mark") Mark mark, @PathVariable Long id, BindingResult result, Model model){
        addMarkFormValidator.validate(mark, result);
        if(result.hasErrors()){
            model.addAttribute("usersList", usersService.getUsers());
            return "/mark/edit";
        }

        Mark originalMark = marksService.getMark(id);
        // modificar solo score y description
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        originalMark.setUser(mark.getUser());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/"+id;
    }

    @RequestMapping("/mark/list/update")
    public String updateList(Model model){
        model.addAttribute("markList", marksService.getMarks());
        return "mark/list::tableMarks";
    }


}
