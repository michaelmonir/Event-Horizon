package com.EventHorizon.EventHorizon.Entities.EventEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
