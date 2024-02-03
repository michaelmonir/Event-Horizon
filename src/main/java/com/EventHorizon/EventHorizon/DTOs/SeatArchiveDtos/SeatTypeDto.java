package com.EventHorizon.EventHorizon.DTOs.SeatArchiveDtos;

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
    int numberOfSeats;
}
