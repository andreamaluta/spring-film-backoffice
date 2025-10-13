package org.lesson.java.spring_film_backoffice.repo;

import org.lesson.java.spring_film_backoffice.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    public Genre findByName(String name);

}
