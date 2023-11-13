package com.EventHorizon.EventHorizon.DTO;


import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModeratorDTO {
    private int id;
    private InformationDTO informationDTO;


    public static ModeratorDTO toDTO(Moderator moderator) {
        return ModeratorDTO.builder()
                .id(moderator.getId())
                .informationDTO(InformationDTO.toDTO(moderator.getInformation())).build();
    }
}
