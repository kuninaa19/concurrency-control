package com.event.fcfssystem.event;

import com.event.fcfssystem.event.dto.request.CreateEventRequestDto;
import com.event.fcfssystem.event.dto.response.CreateEventResponseDto;
import com.event.fcfssystem.event.dto.response.FindEventResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("{id}")
    public FindEventResponseDto findOne(@PathVariable Long id){
        return this.eventService.findOne(id);
    }

    @PostMapping("")
    public CreateEventResponseDto save(@RequestBody CreateEventRequestDto createEventRequestDto) {
        return this.eventService.save(createEventRequestDto);
    }
}
