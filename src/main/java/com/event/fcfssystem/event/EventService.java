package com.event.fcfssystem.event;

import com.event.fcfssystem.event.dto.request.CreateEventRequestDto;
import com.event.fcfssystem.event.dto.response.CreateEventResponseDto;
import com.event.fcfssystem.event.dto.response.FindEventResponseDto;
import com.event.fcfssystem.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository repository) {
        this.eventRepository = repository;
    }

    public FindEventResponseDto findOne(Long id) {
        Event event = this.eventRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return FindEventResponseDto.of(event);
    }

    public CreateEventResponseDto save(CreateEventRequestDto createEventRequestDto) {
        Event event = this.eventRepository.save(createEventRequestDto.toEntity());

        return CreateEventResponseDto.of(event);
    }
}
