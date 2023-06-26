package com.event.fcfssystem.ticket.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTicketRequestDto {
    private Long customerId;
    private Long eventId;
}
