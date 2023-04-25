package com.microservice.UserService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    private String hotelId;

    private String name;

    private String location;

    private String about;
}
