package com.robotdr.hotel_api.servis;

import com.robotdr.hotel_api.domain.Booking;
import com.robotdr.hotel_api.domain.Room;
import com.robotdr.hotel_api.dto.RoomDto;
import com.robotdr.hotel_api.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    public final RoomRepository roomRepository;

    public void save(Room room) {
        roomRepository.save(room);
    }
    public void delete (Room room) {
        roomRepository.delete(room);
    }

    public List<RoomDto> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomService::buildRoomDto)
                .collect(Collectors.toList());
    }

    private static RoomDto buildRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .roomName(room.getRoomName())
                .bookingsId(room.getBookings().stream()
                        .map(Booking::getId)
                        .map(Object::toString)
                        .collect(Collectors.toList()))
                .build();
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
    public void deleteById(Long id) {
       roomRepository.deleteById(id);
    }

    public List<RoomDto> findFreeRoom(LocalDate dataStart, LocalDate dataEnd) {
        return roomRepository.findFreeRooms(dataStart,dataEnd).stream()
                .map(RoomService::buildRoomDto)
                .collect(Collectors.toList());
    }
}
