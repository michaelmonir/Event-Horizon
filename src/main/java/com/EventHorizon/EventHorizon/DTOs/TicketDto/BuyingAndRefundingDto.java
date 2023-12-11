package com.EventHorizon.EventHorizon.DTOs.TicketDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuyingAndRefundingDto {
    int launchedEventId;
    int seatTypeId;
    int numOfTickets;

}
