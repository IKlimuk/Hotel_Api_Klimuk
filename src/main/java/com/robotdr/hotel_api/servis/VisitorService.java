package com.robotdr.hotel_api.servis;

import com.robotdr.hotel_api.domain.Booking;
import com.robotdr.hotel_api.domain.Room;
import com.robotdr.hotel_api.domain.Visitor;
import com.robotdr.hotel_api.dto.RoomDto;
import com.robotdr.hotel_api.dto.VisitorDto;
import com.robotdr.hotel_api.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorService {
    public final VisitorRepository visitorRepository;

    public void save(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    public void delete(Visitor visitor) {
        visitorRepository.delete(visitor);
    }

    public Optional<Visitor> findById(Long id) {
        return visitorRepository.findById(id);
    }

    public List<VisitorDto> findAll() {
        return visitorRepository.findAll().stream()
                .map(VisitorService::buildVisitorDto)
                .collect(Collectors.toList());
    }

    private static VisitorDto buildVisitorDto(Visitor visitor) {
        return VisitorDto.builder()
                .id(visitor.getId())
                .firstName(visitor.getFirstName())
                .lastName(visitor.getLastName())
                .passport(visitor.getPassport())
                .bookingsId(visitor.getBookings().stream()
                        .map(Booking::getId)
                        .collect(Collectors.toList()))
                .build();
    }


}
