package org.lesson.java.spring_film_backoffice.repo;

import java.util.List;

import org.lesson.java.spring_film_backoffice.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    public List<Film> findByTitleContaining(String name);

    public List<Film> findAllByOrderByTitleAsc();

}
