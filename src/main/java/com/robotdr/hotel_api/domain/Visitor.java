package com.robotdr.hotel_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String passport;

    @ManyToMany
    @JoinTable(name = "table_B_V",
            joinColumns = @JoinColumn(name = "visitor_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    private List<Booking> bookings;

}
