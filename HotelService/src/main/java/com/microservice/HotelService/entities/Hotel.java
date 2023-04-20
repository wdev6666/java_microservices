package com.microservice.HotelService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "micro_hotels")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    @Id
    @Column(name = "ID")
    private String hotelId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "ABOUT")
    private String about;
}
