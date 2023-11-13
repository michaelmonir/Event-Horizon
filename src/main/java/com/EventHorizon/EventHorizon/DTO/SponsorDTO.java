package com.EventHorizon.EventHorizon.DTO;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.entity.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SponsorDTO {
    private int id;
    private InformationDTO informationDTO;


    public static SponsorDTO toDTO(Sponsor sponsor) {
        return SponsorDTO.builder()
                .id(sponsor.getId())
                .informationDTO(InformationDTO.toDTO(sponsor.getInformation())).build();
    }

}
