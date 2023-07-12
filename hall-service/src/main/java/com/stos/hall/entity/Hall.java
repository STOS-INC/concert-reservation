package com.stos.hall.entity;

import java.util.List;

public class Hall {
    private final Long id;
    private final String name;
    private final List<Seat> seats;

    public Hall(Long id, String name, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.seats = seats;
    }
}
