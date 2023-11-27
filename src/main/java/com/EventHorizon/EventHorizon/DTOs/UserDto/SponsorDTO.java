package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SponsorDTO  extends UserDTO {
    private int id;

    @Override
    void fromUser(User user) {
        Sponsor sponsor = (Sponsor) user;
        this.id = sponsor.getId();
    }
}
