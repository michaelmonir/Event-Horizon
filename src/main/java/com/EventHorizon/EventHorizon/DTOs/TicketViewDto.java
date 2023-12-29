package com.EventHorizon.EventHorizon.DTOs;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class TicketViewDto
{
    int seatTypeId;
    String name;
    int totalNumberOfSeats;
    int availableNumberOfSeats;
    int price;
    int numberOfBuyedTickets;
}
