package org.lesson.java.spring_film_backoffice.controllers;

import java.util.List;
import java.util.Optional;

import org.lesson.java.spring_film_backoffice.model.Film;
import org.lesson.java.spring_film_backoffice.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/films")
public class FilmRestController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public List<Film> index(@RequestParam(name = "title", required = false) String name) {

        if (name == null || name.isEmpty()) {
            return filmService.findAll();
        } else {
            return filmService.findByTitle(name);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> show(@PathVariable Integer id) {

        Optional<Film> filmAttempt = filmService.findById(id);

        if (filmAttempt.isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Film>(filmAttempt.get(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Film> store(@RequestBody Film film) {
        return new ResponseEntity<Film>(filmService.create(film), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> update(@RequestBody Film film, @PathVariable Integer id) {

        if (filmService.findById(id).isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }

        film.setId(id);
        return new ResponseEntity<Film>(filmService.update(film), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Film> delete(@PathVariable Integer id) {
        if (filmService.findById(id).isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }

        filmService.deleteById(id);
        return new ResponseEntity<Film>(HttpStatus.OK);

    }

}
