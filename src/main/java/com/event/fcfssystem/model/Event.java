package com.event.fcfssystem.model;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "rest_quantity")
    private int restQuantity;

    public static Event createEvent(String name, int quantity) {
        Event event = new Event();

        event.name = name;
        event.quantity = quantity;
        event.restQuantity = quantity;

        return event;
    }

    public void decreaseRestQuantity(int value) {
        restQuantity -= value;

        if(restQuantity < 0){
            throw new IllegalArgumentException("티켓 발급 수량을 넘었습니다.");
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRestQuantity() {
        return restQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return quantity == event.quantity && restQuantity == event.restQuantity && Objects.equals(id, event.id) && Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, restQuantity);
    }
}
