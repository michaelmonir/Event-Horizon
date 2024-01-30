package com.EventHorizon.EventHorizon.Entities.UpdateUsers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "moderator")
@EqualsAndHashCode(callSuper = true)
public class Moderator extends User {

}
