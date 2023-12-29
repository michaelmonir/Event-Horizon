package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchives;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.SeatArchive.SeatTypeNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.SeatTypeRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SeatTypeRepositoryServiceTest
{
    @Autowired
    SeatTypeRepositoryService seatTypeRepositoryService;
    @Autowired
    private SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;

    @Test
    public void testGetByIdSuccessfull()
    {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        SeatType resultSeatType = this.seatTypeRepositoryService.getById(seatType.getId());
        Assertions.assertEquals(resultSeatType, seatType);
    }

    @Test
    public void testGetByIdWithoutSavingSeatType()
    {
        SeatType seatType = new SeatType("a", 1, 1);
        Assertions.assertThrows(SeatTypeNotFoundException.class, () ->
                this.seatTypeRepositoryService.getById(seatType.getId()));
    }
}
