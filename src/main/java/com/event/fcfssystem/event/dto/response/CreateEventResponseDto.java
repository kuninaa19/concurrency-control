package com.event.fcfssystem.event.dto.response;

import com.event.fcfssystem.model.Event;
import lombok.Getter;

@Getter
public class CreateEventResponseDto {
    private Long id;
    private String name;
    private int quantity;
    private int restQuantity;

    public static CreateEventResponseDto of(Event event) {
        CreateEventResponseDto createEventResponseDto = new CreateEventResponseDto();
        createEventResponseDto.id = event.getId();
        createEventResponseDto.name = event.getName();
        createEventResponseDto.quantity = event.getQuantity();
        createEventResponseDto.restQuantity = event.getRestQuantity();

        return createEventResponseDto;
    }
}
