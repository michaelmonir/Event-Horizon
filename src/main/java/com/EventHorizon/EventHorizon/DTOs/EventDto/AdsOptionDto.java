package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
