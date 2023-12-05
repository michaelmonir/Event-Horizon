package com.EventHorizon.EventHorizon.Entities.EventEntities;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
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
}
