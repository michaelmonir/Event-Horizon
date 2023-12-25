package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import lombok.*;

@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SeatTypeDto
{
    String name;
    int price;

    public SeatTypeDto(SeatType seatType)
    {
        this.name = seatType.getName();
        this.price = seatType.getPrice();
    }
}
