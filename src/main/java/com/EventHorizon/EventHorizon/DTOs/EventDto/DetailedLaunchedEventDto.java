package com.EventHorizon.EventHorizon.DTOs.EventDto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailedLaunchedEventDto extends DetailedEventDto{

    private Date launchedDate;
}
