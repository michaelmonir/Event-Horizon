package com.EventHorizon.EventHorizon.entity.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BuyedTicketCollectionEntityTest
{
    @Test
    public void constructorSuccess()
    {
        SeatType seatType = new SeatType("s1", 1, 1);
        Assertions.assertDoesNotThrow(()-> new BuyedTicketCollection(new Client(), seatType, 0));
    }

    @Test
    public void constructorWithNullSeatType()
    {
        Assertions.assertDoesNotThrow(()-> new BuyedTicketCollection(null, null, 0));
    }
}
