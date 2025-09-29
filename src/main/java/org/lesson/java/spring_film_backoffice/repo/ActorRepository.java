package org.lesson.java.spring_film_backoffice.repo;

import org.lesson.java.spring_film_backoffice.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
