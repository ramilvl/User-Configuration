package com.example.spring.controller;

import com.example.spring.model.Person;
import com.example.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping({"/", "list"})
    public String getAllPersons(Model model)
    {
        model.addAttribute("persons",personService.list());
        return "index";
    }

    @GetMapping("/newPerson")
    public ModelAndView showNewPersonPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("new_person");
        mv.addObject("person", new Person());
        return mv;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Person person){
        personService.save(person);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdatePage(@PathVariable String id){
        Person found = personService.getById(id);
        ModelAndView mv = new ModelAndView("update_person");
        mv.addObject("person", found);
        return mv;
    }
}
