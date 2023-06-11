package com.robotdr.hotel_api.dto;

import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
public class RoomDto {
    private Long id;

    private String roomName;

    private List<String> bookingsId;
}
