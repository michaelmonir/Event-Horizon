package com.EventHorizon.EventHorizon.DTOs.EventDto.EventRelated;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import lombok.*;

@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdsOptionDto
{
    public int id;
    public String name;
    public AdsOptionDto(AdsOption adsOption){
        this.id=adsOption.getId();
        this.name=adsOption.getName();
    }
}
