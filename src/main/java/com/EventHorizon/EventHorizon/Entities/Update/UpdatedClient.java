package com.EventHorizon.EventHorizon.Entities.Update;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "client")
public class UpdatedClient extends UpdatedUser {

}
