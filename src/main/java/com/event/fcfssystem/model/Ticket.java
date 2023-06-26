package com.event.fcfssystem.model;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TICKET")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @PrePersist
    public void beforeSave() {
        generateSerialNumber();
        decreaseRestQuantity();
    }

    private void decreaseRestQuantity() {
        if (event != null) {
            event.decreaseRestQuantity(1);
        }
    }

    private void generateSerialNumber() {
        if (this.serialNumber != null) {
            throw new IllegalArgumentException("Already exist");
        }

        String uuid = UUID.randomUUID().toString();
        this.serialNumber = uuid + new Date().getTime();
    }

    public static Ticket createTicket(Customer customer, Event event) {
        Ticket ticket = new Ticket();
        ticket.customer = customer;
        ticket.event = event;

        return ticket;
    }

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}