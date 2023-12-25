package com.EventHorizon.EventHorizon.entity.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.GiftedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GiftedTicketCollectionEntityTest
{
    @Test
    public void constructorSuccess()
    {
        SeatType seatType = new SeatType("s1", 1, 1);
        Assertions.assertDoesNotThrow(()-> new GiftedTicketCollection(new Client(), seatType, new Sponsor(), 0));
    }

    @Test
    public void constructorWithNullSeatType()
    {
        Assertions.assertDoesNotThrow(()-> new GiftedTicketCollection(null, null, null, 0));
    }
}
