package com.EventHorizon.EventHorizon.Entities.Event;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "adsoption")
@EqualsAndHashCode
public class AdsOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int priority;
    @Column(nullable = false)
    private String name;
}
