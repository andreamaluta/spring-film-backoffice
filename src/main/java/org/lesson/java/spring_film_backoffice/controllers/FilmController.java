package org.lesson.java.spring_film_backoffice.controllers;

import org.lesson.java.spring_film_backoffice.model.Film;
import org.lesson.java.spring_film_backoffice.repo.FilmRepository;
import org.lesson.java.spring_film_backoffice.service.ActorService;
import org.lesson.java.spring_film_backoffice.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("filmList", filmService.findAll());

        return "/films/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {

        model.addAttribute("film", filmService.getById(id));
        model.addAttribute("actors", filmService.getById(id).getActors());

        return "/films/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("film", new Film());
        model.addAttribute("actors", actorService.findAll());

        return "/films/create-or-edit";

    }

    @PostMapping("/create")
    private String store(@Valid @ModelAttribute("film") Film formFilm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/films/create-or-edit";
        }

        filmService.create(formFilm);

        return "redirect:/films";

    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        System.out.println("Data: " + filmService.getById(id).getPublication_date());
        model.addAttribute("film", filmService.getById(id));
        model.addAttribute("edit", true);
        model.addAttribute("actors", actorService.findAll());

        return "/films/create-or-edit";

    }

    @PostMapping("/edit/{id}")
    private String update(@Valid @ModelAttribute("film") Film formFilm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/films/create-or-edit";
        }

        filmService.update(formFilm);

        return "redirect:/films";

    }

    @PostMapping("delete/{id}")
    private String delete(Model model, @PathVariable Integer id) {
        filmService.deleteById(id);
        return "redirect:/films";
    }

}
