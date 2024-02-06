package com.EventHorizon.EventHorizon.Entities.Event;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<SeatType> seatTypes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event that = (Event) o;

        // not comparing dates as they are compared in different formats so will always return false
        return id == that.getId()
                && Objects.equals(name, that.getName())
                && Objects.equals(description, that.getDescription())
                && Objects.equals(eventCategory, that.getEventCategory())
                && eventType == that.getEventType()
                && Objects.equals(eventLocation, that.getEventLocation())
                && Objects.equals(eventAds, that.getEventAds())
                && Objects.equals(eventOrganizer, that.getEventOrganizer());
    }
}
