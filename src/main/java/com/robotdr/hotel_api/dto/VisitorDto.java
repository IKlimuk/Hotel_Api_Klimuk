package com.robotdr.hotel_api.dto;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class VisitorDto {
    private  Long id;

    private String firstName;

    private String lastName;

    private String passport;

    private List<Long> bookingsId;
}
