package com.robotdr.hotel_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate checkIn;
    @Column
    private LocalDate checkOut;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToMany
    @JoinTable(name = "visitors",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "visitor_id"))
    private List<Visitor> visitors;

}
