package com.EventHorizon.EventHorizon.Entities.EventEntities;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(nullable = false)
    protected String name;
    protected String description;
    protected String eventCategory;
    protected EventType eventType;
    protected Date eventDate;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "location_id",
            referencedColumnName = "id"
    )
    protected Location eventLocation;
    @ManyToOne(
    )
    @JoinColumn(
            name = "ads_id",
            referencedColumnName = "id",
            nullable = false
    )
    protected AdsOption eventAds;
    @ManyToOne(
    )
    @JoinColumn(
            name = "organizer_id",
            referencedColumnName = "id",
            nullable = false
    )
    protected Organizer eventOrganizer;
}
