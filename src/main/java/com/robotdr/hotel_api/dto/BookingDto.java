package com.robotdr.hotel_api.dto;

import com.robotdr.hotel_api.domain.Room;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class BookingDto {

    private Long id;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private String roomName;

    private List<String> lastNames;
}
