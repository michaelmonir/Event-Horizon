package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClientDTO extends UserDTO {
    private int id;


    @Override
    void fromUser(User user) {
        Client client = (Client) user;
        this.id = client.getId();
    }
}