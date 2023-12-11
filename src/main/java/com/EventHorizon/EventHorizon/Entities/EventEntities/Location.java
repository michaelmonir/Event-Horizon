package com.EventHorizon.EventHorizon.Entities.EventEntities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "Location")
@EqualsAndHashCode
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    private String address;

    public Location(String country, String city, String address) {
        this.country = country;
        this.city = city;
        this.address = address;
    }
}
