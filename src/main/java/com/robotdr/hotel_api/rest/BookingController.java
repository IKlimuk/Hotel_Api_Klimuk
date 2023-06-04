package com.robotdr.hotel_api.rest;

import com.robotdr.hotel_api.domain.Booking;
import com.robotdr.hotel_api.dto.BookingDto;
import com.robotdr.hotel_api.servis.BookingServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingServise bookingServise;

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDto>> findAll() {
        return ResponseEntity.ok(bookingServise.findAll());
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        return bookingServise.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/bookings")
    public ResponseEntity<Void> save(@RequestBody Booking booking) {
        bookingServise.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
