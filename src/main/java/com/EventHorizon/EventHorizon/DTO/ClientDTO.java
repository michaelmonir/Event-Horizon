package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClientDTO {
    private int id;
    private InformationDTO informationDTO;

    public static ClientDTO toDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .informationDTO(InformationDTO.toDTO(client.getInformation()))
                .build();
    }
}