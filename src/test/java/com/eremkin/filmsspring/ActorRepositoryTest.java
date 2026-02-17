package com.eremkin.filmsspring;

import com.eremkin.filmsspring.model.Actor;
import com.eremkin.filmsspring.repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void testSaveAndFindActor() {
        Actor actor = new Actor();
        actor.setFirstName("Иван");
        actor.setLastName("Иванов");
        actorRepository.save(actor);

        List<Actor> actors = actorRepository.findAll();
        assertFalse(actors.isEmpty());
        assertEquals("Иван", actors.getLast().getFirstName());
    }
}
