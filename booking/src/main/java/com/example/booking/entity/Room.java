package com.example.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer number;
    private Double price;
    private Integer capacity;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UnavailableDate> unavailableDates = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @OneToMany
    @JoinColumn(name = "booking_id")
    private Set<Booking> bookings;
}
