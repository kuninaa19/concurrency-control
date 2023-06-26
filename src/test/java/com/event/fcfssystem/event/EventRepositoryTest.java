package com.event.fcfssystem.event;

import com.event.fcfssystem.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles(value={"test"})
public class EventRepositoryTest {
    private final EventRepository eventRepository;

    @Autowired
    public EventRepositoryTest(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Test
    void testSaveEvent() {
        Event testEvent = Event.createEvent("특별 이벤트", 2);

        Event savedEvent = eventRepository.save(testEvent);

        Optional<Event> expected = eventRepository.findById(1L);
        assertThat(savedEvent).isEqualTo(expected.get());
    }
}
