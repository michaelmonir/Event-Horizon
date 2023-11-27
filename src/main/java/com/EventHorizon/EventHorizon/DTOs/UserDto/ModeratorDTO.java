package com.EventHorizon.EventHorizon.DTOs.UserDto;


import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
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
