package com.EventHorizon.EventHorizon.Entities.Event;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Table(name = "launched_event")
public class LaunchedEvent extends Event {

    @Column(nullable = false)
    @Builder.Default
    private Date launchedDate = Calendar.getInstance().getTime();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaunchedEvent that = (LaunchedEvent) o;
        return super.equals(that);
    }
}
