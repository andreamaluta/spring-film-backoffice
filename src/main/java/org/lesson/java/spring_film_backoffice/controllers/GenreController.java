package org.lesson.java.spring_film_backoffice.controllers;

import org.lesson.java.spring_film_backoffice.model.Genre;
import org.lesson.java.spring_film_backoffice.repo.GenreRepository;
import org.lesson.java.spring_film_backoffice.service.GenreService;
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
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("genreList", genreService.findAll());

        return "/genres/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Integer id) {

        model.addAttribute("genreItem", genreService.getById(id));
        model.addAttribute("genreFilms", genreService.getById(id).getFilms());

        return "/genres/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("genre", new Genre());

        return "/genres/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genres") Genre formGenre, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/genres/create-or-edit";
        }

        genreService.create(formGenre);

        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {

        model.addAttribute("genre", genreService.getById(id));
        model.addAttribute("edit", true);

        return "genres/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("genres") Genre formGenre, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "genres/create-or-edit";
        }

        genreService.update(formGenre);

        return "redirect:/genres";
    }

    @PostMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id) {

        genreService.deleteById(id);

        return "redirect:/genres";
    }

}
