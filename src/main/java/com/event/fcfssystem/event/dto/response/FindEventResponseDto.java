package com.event.fcfssystem.event.dto.response;

import com.event.fcfssystem.model.Event;
import lombok.Getter;

@Getter
public class FindEventResponseDto {
    private Long id;
    private String name;
    private int quantity;
    private int restQuantity;

    public static FindEventResponseDto of(Event event) {
        FindEventResponseDto findEventResponseDto = new FindEventResponseDto();
        findEventResponseDto.id = event.getId();
        findEventResponseDto.name = event.getName();
        findEventResponseDto.quantity = event.getQuantity();
        findEventResponseDto.restQuantity = event.getRestQuantity();

        return findEventResponseDto;
    }
}
