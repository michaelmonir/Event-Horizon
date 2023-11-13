package com.EventHorizon.EventHorizon.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="location_id",
            referencedColumnName="id"
    )
    private Location eventLocation;
    @ManyToOne(
    )
    @JoinColumn(
            name="ads_id",
            referencedColumnName="id"
    )
    private AdsOption eventAds;
}
