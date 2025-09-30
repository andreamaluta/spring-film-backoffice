package org.lesson.java.spring_film_backoffice.service;

import java.util.List;

import org.lesson.java.spring_film_backoffice.model.Film;
import org.lesson.java.spring_film_backoffice.model.Genre;
import org.lesson.java.spring_film_backoffice.repo.FilmRepository;
import org.lesson.java.spring_film_backoffice.repo.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final FilmRepository filmRepository;

    @Autowired
    private GenreRepository genreRepository;

    GenreService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre getById(int id) {
        return genreRepository.findById(id).get();
    }

    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

    public void deleteById(int id) {

        Genre genreToDelete = genreRepository.findById(id).get();

        for (Film filmToDelete : genreToDelete.getFilms()) {
            filmRepository.delete(filmToDelete);
        }

        genreRepository.delete(genreToDelete);
    }

}
