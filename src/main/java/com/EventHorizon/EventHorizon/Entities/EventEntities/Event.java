package com.EventHorizon.EventHorizon.Entities.EventEntities;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "location_id",
            referencedColumnName = "id"
    )
    private Location eventLocation;
    @ManyToOne(
    )
    @JoinColumn(
            name = "ads_id",
            referencedColumnName = "id",
            nullable = false
    )
    private AdsOption eventAds;
    @ManyToOne(
    )
    @JoinColumn(
            name = "organizer_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Organizer eventOrganizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SeatType> seatTypes;
}
