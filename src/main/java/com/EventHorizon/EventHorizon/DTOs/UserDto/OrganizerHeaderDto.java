package com.EventHorizon.EventHorizon.DTOs.UserDto;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizerHeaderDto {
    private int id;
    private String name;

    public OrganizerHeaderDto(Organizer organizer) {
        this.id = organizer.getId();
        this.name = organizer.getInformation().userName;
    }

}
