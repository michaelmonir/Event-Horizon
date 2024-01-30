package com.EventHorizon.EventHorizon.entity.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Sponsor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SponsorSeatArchiveTest {

    @Test
    public void constructorSuccessfull() {

        SeatType seatType = new SeatType("s", 1, 1);
        Sponsor sponsor = new Sponsor();
        Assertions.assertDoesNotThrow(()->
                new SponsorSeatArchive(seatType, sponsor, 1, 1));
    }

    @Test
    public void constructorWithNullSeatType() {

        Sponsor sponsor = new Sponsor();
        Assertions.assertDoesNotThrow(()->
                new SponsorSeatArchive(null, sponsor, 1, 1));
    }

    @Test
    public void constructorWithNullSponsor() {

        SeatType seatType = new SeatType("s", 1, 1);
        Assertions.assertDoesNotThrow(()->
                new SponsorSeatArchive(seatType, null, 1, 1));
    }
}
