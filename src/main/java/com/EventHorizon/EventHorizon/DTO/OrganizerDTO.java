package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
