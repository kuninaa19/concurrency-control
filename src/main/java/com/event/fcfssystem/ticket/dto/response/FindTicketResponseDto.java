package com.event.fcfssystem.ticket.dto.response;

import com.event.fcfssystem.model.Ticket;
import lombok.Getter;

import java.util.Date;

@Getter
public class FindTicketResponseDto {

    private Long id;
    private Date createdAt;
    private String serialNumber;

    public static FindTicketResponseDto of(Ticket ticket) {
        FindTicketResponseDto createTicketResponseDto = new FindTicketResponseDto();
        createTicketResponseDto.id = ticket.getId();
        createTicketResponseDto.createdAt = ticket.getCreatedAt();
        createTicketResponseDto.serialNumber = ticket.getSerialNumber();

        return createTicketResponseDto;
    }
}
