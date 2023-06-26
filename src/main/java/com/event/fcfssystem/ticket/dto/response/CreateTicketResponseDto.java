package com.event.fcfssystem.ticket.dto.response;

import com.event.fcfssystem.model.Ticket;
import lombok.Getter;

import java.util.Date;

@Getter
public class CreateTicketResponseDto {
    private Long id;
    private String eventName;
    private String customerName;
    private Date createdAt;

    public static CreateTicketResponseDto of(Ticket ticket) {
        CreateTicketResponseDto createTicketResponseDto = new CreateTicketResponseDto();
        createTicketResponseDto.id = ticket.getId();
        createTicketResponseDto.eventName = ticket.getEvent().getName();
        createTicketResponseDto.customerName = ticket.getCustomer().getEmail();
        createTicketResponseDto.createdAt = ticket.getCreatedAt();

        return createTicketResponseDto;
    }
}
