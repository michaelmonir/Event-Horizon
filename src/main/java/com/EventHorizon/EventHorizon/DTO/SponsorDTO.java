package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.entity.Sponsor;
import com.EventHorizon.EventHorizon.entity.User;
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
