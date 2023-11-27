package com.EventHorizon.EventHorizon.DTO;


import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModeratorDTO  extends UserDTO  {
    private int id;


    @Override
    void fromUser(User user) {
        Moderator moderator = (Moderator) user;
        this.id = moderator.getId();
    }
}
