package org.lesson.java.spring_film_backoffice.repo;

import org.lesson.java.spring_film_backoffice.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {

}
