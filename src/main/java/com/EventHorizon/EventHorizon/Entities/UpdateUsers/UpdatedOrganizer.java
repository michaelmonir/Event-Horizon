package com.EventHorizon.EventHorizon.Entities.UpdateUsers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "organizer")
public class UpdatedOrganizer extends UpdatedUser {
    @Column(name = "rate")
    private double rate;
}
