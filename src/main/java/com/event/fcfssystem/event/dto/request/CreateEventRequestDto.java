package com.event.fcfssystem.event.dto.request;

import com.event.fcfssystem.model.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventRequestDto {
    private String name;
    private int quantity;

    public Event toEntity() {
        return Event.createEvent(name, quantity);
    }
}
