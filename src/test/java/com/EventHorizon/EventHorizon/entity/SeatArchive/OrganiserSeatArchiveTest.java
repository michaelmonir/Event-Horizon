package com.EventHorizon.EventHorizon.entity.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrganiserSeatArchiveTest
{
    @Test
    public void constructorSuccess()
    {
        SeatType seatType = new SeatType("s1", 1);
        Assertions.assertDoesNotThrow(()-> new OrganizerSeatArchive(seatType, 1, 1));
    }

    @Test
    public void constructorWithNullSeatType()
    {
        Assertions.assertThrows(NullPointerException.class, ()-> new OrganizerSeatArchive(null, 1, 1));
    }
}
