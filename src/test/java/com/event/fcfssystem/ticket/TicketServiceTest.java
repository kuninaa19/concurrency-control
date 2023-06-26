package com.event.fcfssystem.ticket;

import com.event.fcfssystem.customer.CustomerRepository;
import com.event.fcfssystem.event.EventRepository;
import com.event.fcfssystem.event.EventService;
import com.event.fcfssystem.event.dto.request.CreateEventRequestDto;
import com.event.fcfssystem.event.dto.response.CreateEventResponseDto;
import com.event.fcfssystem.model.Event;
import com.event.fcfssystem.ticket.dto.request.CreateTicketRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = {"test"})
public class TicketServiceTest {

    public static final int THREADS_NUM = 5;
    public static final int QUANTITY = 1;

    private final TicketService ticketService;
    private final EventRepository eventRepository;
    private final CustomerRepository customerRepository;
    private final EventService eventService;
    private int count;
    private Long id;

    @Autowired
    public TicketServiceTest(EventService eventService, TicketService ticketService, EventRepository eventRepository, CustomerRepository customerRepository) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.eventRepository = eventRepository;
        this.customerRepository = customerRepository;
    }

    private int getRandomNumberUsingInts(int max) {
        Random random = new Random();
        return random.ints(0, max)
                .findFirst()
                .getAsInt();
    }

    @BeforeEach
    @DisplayName("1개의 티켓을 발급할 수 있는 이벤트 생성")
    void createEvent() {
        CreateEventRequestDto createEventRequestDto = new CreateEventRequestDto();
        createEventRequestDto.setName("특별 이벤트");
        createEventRequestDto.setQuantity(QUANTITY);

        CreateEventResponseDto event = eventService.save(createEventRequestDto);
        id = event.getId();
    }

    @Test
    @DisplayName("5명의 유저가 티켓 수량이 1개 발급을 요청하고 1명만 GET 해야한다.")
    void testSaveTicket() throws InterruptedException {
        count = (int) customerRepository.count();
        CountDownLatch latch = new CountDownLatch(THREADS_NUM);

        for (int i = 0; i < THREADS_NUM; i++) {
            new Thread(() -> {
                try {
                    CreateTicketRequestDto createTicketRequestDto = new CreateTicketRequestDto();
                    createTicketRequestDto.setCustomerId((long) getRandomNumberUsingInts(count));
                    createTicketRequestDto.setEventId(id);

                    ticketService.save(createTicketRequestDto);
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await();

        Event event = eventRepository.findById(id).orElseThrow();
        assertThat(event.getRestQuantity()).isEqualTo(event.getQuantity() - QUANTITY);
    }
}
