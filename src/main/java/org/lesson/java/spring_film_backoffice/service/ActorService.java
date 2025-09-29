package org.lesson.java.spring_film_backoffice.service;

import java.util.List;

import org.lesson.java.spring_film_backoffice.model.Actor;
import org.lesson.java.spring_film_backoffice.repo.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor getById(int id) {
        return actorRepository.findById(id).get();
    }

    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor update(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteById(int id) {

        Actor actor = actorRepository.findById(id).get();
        actorRepository.delete(actor);

    }

}
