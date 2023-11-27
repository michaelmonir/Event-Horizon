package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UserEntities.User;

public abstract class UserDTO {
    abstract void fromUser(User user);
}
