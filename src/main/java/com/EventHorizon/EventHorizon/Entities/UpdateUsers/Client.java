package com.EventHorizon.EventHorizon.Entities.UpdateUsers;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "client")
public class Client extends User {
}
