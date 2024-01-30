package com.EventHorizon.EventHorizon.DTOs.UserDto;


import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;

public abstract class UserCreationDTO {
    abstract void fromUser(User user);
}
