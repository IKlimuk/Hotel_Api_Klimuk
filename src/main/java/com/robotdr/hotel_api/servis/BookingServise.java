package com.robotdr.hotel_api.servis;

import com.robotdr.hotel_api.domain.Booking;
import com.robotdr.hotel_api.domain.Room;
import com.robotdr.hotel_api.domain.Visitor;
import com.robotdr.hotel_api.dto.BookingDto;
import com.robotdr.hotel_api.dto.RoomDto;
import com.robotdr.hotel_api.repository.BookingRepository;
import com.robotdr.hotel_api.repository.RoomRepository;
import com.robotdr.hotel_api.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServise {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final VisitorRepository visitorRepository;


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
        var roomName = "-";
        if (booking.getRoom() != null) {
            roomName = booking.getRoom().getRoomName();
        }
        return BookingDto.builder()
                .id(booking.getId())
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .roomName(roomName)
                .lastNames(booking.getVisitors().stream()
                        .map(Visitor::getLastName)
                        .collect(Collectors.toList()))
                .build();
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public void addRoom(Long bookingId, Long roomId) {
        var room = roomRepository.findById(roomId).get();
        var booking = bookingRepository.findById(bookingId).get();
        booking.setRoom(room);
        bookingRepository.save(booking);
    }

    public void addVisitor(Long bookingId, Long visitorId) {
        var visitor = visitorRepository.findById(visitorId).get();
        var booking = bookingRepository.findById(bookingId).get();
        booking.getVisitors().add(visitor);
        bookingRepository.save(booking);
    }

    public void changeRoom(Long bookingId, Long roomId) {
        var booking = bookingRepository.findById(bookingId).get();
        var room = roomRepository.findById(roomId).get();
        booking.setRoom(room);
        bookingRepository.save(booking);
    }

}
