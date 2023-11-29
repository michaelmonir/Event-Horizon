package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizerDTO   extends UserDTO {

    private int id;
    private double rate;

    @Override
    void fromUser(User  user) {
        Organizer organizer =  (Organizer) user;
        this.id = organizer.getId();
        this.rate=organizer.getRate();
    }
}
