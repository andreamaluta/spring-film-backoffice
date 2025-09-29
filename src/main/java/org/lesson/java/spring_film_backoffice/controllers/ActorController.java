package org.lesson.java.spring_film_backoffice.controllers;

import org.lesson.java.spring_film_backoffice.model.Actor;
import org.lesson.java.spring_film_backoffice.repo.ActorRepository;
import org.lesson.java.spring_film_backoffice.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("actorList", actorService.findAll());

        return "/actors/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {

        model.addAttribute("actor", actorService.getById(id));

        return "/actors/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("actor", new Actor());

        return "/actors/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("actor") Actor formActor, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/actors/create-or-edit";
        }

        actorService.create(formActor);

        return "redirect:/actors";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("actor", actorService.getById(id));
        model.addAttribute("edit", true);

        return "/actors/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("actor") Actor formActor, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/actors/create-or-edit";
        }

        actorService.update(formActor);

        return "redirect:/actors";
    }

    @PostMapping("delete/{id}")
    public String delete(Model model, @PathVariable Integer id) {

        actorService.deleteById(id);

        return "redirect:/actors";
    }

}
