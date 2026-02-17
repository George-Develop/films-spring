package com.eremkin.filmsspring;

import com.eremkin.filmsspring.model.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ActorModelTest {

    @Test
    void testActorGettersSetters() {
        Actor actor = new Actor();
        actor.setFirstName("Иван");
        actor.setLastName("Иванов");
        actor.setBirthDate(LocalDate.of(1990, 1, 1));

        Assertions.assertEquals("Иван", actor.getFirstName());
        Assertions.assertEquals("Иванов", actor.getLastName());
        Assertions.assertEquals(LocalDate.of(1990, 1, 1), actor.getBirthDate());
    }
}
