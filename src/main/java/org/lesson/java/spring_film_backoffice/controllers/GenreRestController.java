package org.lesson.java.spring_film_backoffice.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.lesson.java.spring_film_backoffice.model.Film;
import org.lesson.java.spring_film_backoffice.model.Genre;
import org.lesson.java.spring_film_backoffice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/genres")
public class GenreRestController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> index() {
        return genreService.findAll();
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> getFilmsByGenre(@PathVariable int id) {
        try {
            List<Film> films = genreService.getFilmsByGenreId(id);
            return new ResponseEntity<>(films, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
