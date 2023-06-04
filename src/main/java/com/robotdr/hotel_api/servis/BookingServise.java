package com.robotdr.hotel_api.servis;

import com.robotdr.hotel_api.domain.Booking;
import com.robotdr.hotel_api.domain.Room;
import com.robotdr.hotel_api.domain.Visitor;
import com.robotdr.hotel_api.dto.BookingDto;
import com.robotdr.hotel_api.dto.RoomDto;
import com.robotdr.hotel_api.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServise {
    private final BookingRepository bookingRepository;


    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    public List<BookingDto> findAll() {
        return bookingRepository.findAll().stream()
                .map(BookingServise::buildBookingDto)
                .collect(Collectors.toList());
    }

    private static BookingDto buildBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .roomName(booking.getRoom().getRoomName())
                .lastNames(booking.getVisitors().stream()
                        .map(Visitor::getLastName)
                        .collect(Collectors.toList()))
                .build();
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

}
