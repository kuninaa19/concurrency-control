package com.event.fcfssystem.ticket;

import com.event.fcfssystem.ticket.dto.request.CreateTicketRequestDto;
import com.event.fcfssystem.ticket.dto.response.CreateTicketResponseDto;
import com.event.fcfssystem.ticket.dto.response.FindTicketResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("{id}")
    public FindTicketResponseDto findOne(@PathVariable Long id){
        return this.ticketService.findOne(id);
    }

    @PostMapping("")
    public CreateTicketResponseDto save(@RequestBody CreateTicketRequestDto createTicketRequestDto){
        return this.ticketService.save(createTicketRequestDto);
    }
}