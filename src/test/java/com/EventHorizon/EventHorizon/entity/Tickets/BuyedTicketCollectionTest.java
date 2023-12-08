package com.EventHorizon.EventHorizon.entity.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class BuyedTicketCollectionTest
{
    @Test
    public void constructorSuccess()
    {
        SeatType seatType = new SeatType("s1", 1);
        Assertions.assertDoesNotThrow(()-> new BuyedTicketCollection(new Client(), seatType, new Date(), 0));
    }

    @Test
    public void constructorWithNullSeatType()
    {
        Assertions.assertDoesNotThrow(()-> new BuyedTicketCollection(null, null, null, 0));
    }
}
