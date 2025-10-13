package org.lesson.java.spring_film_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.lesson.java.spring_film_backoffice.model.Film;
import org.lesson.java.spring_film_backoffice.repo.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film getById(int id) {
        return filmRepository.findById(id).get();
    }

    public Optional<Film> findById(Integer id) {
        return filmRepository.findById(id);
    }

    public List<Film> findByTitle(String name) {
        return filmRepository.findByTitleContaining(name);
    }

    public Film create(Film film) {
        return filmRepository.save(film);
    }

    public Film update(Film film) {
        return filmRepository.save(film);
    }

    public void deleteById(int id) {

        Film film = filmRepository.findById(id).get();

        filmRepository.delete(film);

    }

}
