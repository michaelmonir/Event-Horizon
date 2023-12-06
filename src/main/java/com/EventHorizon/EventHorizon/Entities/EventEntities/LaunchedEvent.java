package com.EventHorizon.EventHorizon.Entities.EventEntities;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "launched_event")
public class LaunchedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @Builder.Default
    private Date launchedDate = Calendar.getInstance().getTime();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id",
            referencedColumnName = "id",
            nullable = false)
    private Event event;

    public String getName() {
        return event.getName();
    }

    public void setName(String name) {
        this.event.setName(name);
    }

    public String getDescription() {
        return event.getDescription();
    }

    public void setDescription(String description) {
        this.event.setDescription(description);
    }

    public String getEventCategory() {
        return event.getEventCategory();
    }

    public void setEventCategory(String eventCategory) {
        this.event.setEventCategory(eventCategory);
    }

    public EventType getEventType() {
        return event.getEventType();
    }

    public void setEventType(EventType eventType) {
        this.event.setEventType(eventType);
    }

    public Date getEventDate() {
        return event.getEventDate();
    }

    public void setEventDate(Date eventDate) {
        this.event.setEventDate(eventDate);
    }

    public Location getEventLocation() {
        return event.getEventLocation();
    }

    public void setEventLocation(Location eventLocation) {
        this.event.setEventLocation(eventLocation);
    }

    public AdsOption getEventAds() {
        return event.getEventAds();
    }

    public void setEventAds(AdsOption eventAds) {
        this.event.setEventAds(eventAds);
    }

    public Organizer getEventOrganizer() {
        return event.getEventOrganizer();
    }

    public void setEventOrganizer(Organizer eventOrganizer) {
        this.event.setEventOrganizer(eventOrganizer);
    }
}
