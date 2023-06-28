package com.event.fcfssystem.ticket;

import com.event.fcfssystem.aop.DistributedLock;
import com.event.fcfssystem.customer.CustomerRepository;
import com.event.fcfssystem.event.EventRepository;
import com.event.fcfssystem.model.Customer;
import com.event.fcfssystem.model.Event;
import com.event.fcfssystem.model.Ticket;
import com.event.fcfssystem.ticket.dto.request.CreateTicketRequestDto;
import com.event.fcfssystem.ticket.dto.response.CreateTicketResponseDto;
import com.event.fcfssystem.ticket.dto.response.FindTicketResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, EventRepository eventRepository, CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public FindTicketResponseDto findOne(Long id) {
        Ticket aTicket = this.ticketRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return FindTicketResponseDto.of(aTicket);
    }

    @DistributedLock(value = "#createTicketRequestDto.getEventId()")
    @Transactional(timeout = 5, rollbackFor = InterruptedException.class)
    public CreateTicketResponseDto save(CreateTicketRequestDto createTicketRequestDto) {
        Customer customer = customerRepository.findById(createTicketRequestDto.getCustomerId()).orElseThrow(IllegalArgumentException::new);
        Event event = eventRepository.findById(createTicketRequestDto.getEventId()).orElseThrow(IllegalArgumentException::new);

        Ticket aTicket = Ticket.createTicket(customer, event);
        Ticket ticket = this.ticketRepository.save(aTicket);

        return CreateTicketResponseDto.of(ticket);
    }
}
