package com.EventHorizon.EventHorizon.Entities.EventEntities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@Table(name = "drafted_event")
@NoArgsConstructor
public class DraftedEvent extends Event
{
}
