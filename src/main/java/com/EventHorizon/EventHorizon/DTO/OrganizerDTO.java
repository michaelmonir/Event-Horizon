package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.entity.Organizer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizerDTO {

    private int id;
    private double rate;
    private InformationDTO informationDTO;


    public static OrganizerDTO toDTO(Organizer organizer) {
        return OrganizerDTO.builder()
                .id(organizer.getId())
                .rate(organizer.getRate())
                .informationDTO(InformationDTO.toDTO(organizer.getInformation())).build();
    }
}
