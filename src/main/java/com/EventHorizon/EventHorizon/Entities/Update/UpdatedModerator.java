package com.EventHorizon.EventHorizon.Entities.Update;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "moderator")
public class UpdatedModerator extends UpdatedUser{

}
