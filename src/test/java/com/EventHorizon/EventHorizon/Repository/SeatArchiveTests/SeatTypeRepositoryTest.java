package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SeatTypeRepositoryTest
{
    @Autowired
    private SeatTypeRepository seatTypeRepository;
    @Autowired
    private SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;

    @Test
    public void testGetByIdSuccessfull()
    {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        Optional<SeatType> resultSeatType = this.seatTypeRepository.findById(seatType.getId());
        Assertions.assertEquals(resultSeatType.get(), seatType);
    }

    @Test
    public void testGetByIdWithoutSavingSeatType()
    {
        SeatType seatType = new SeatType("a", 1, 1);
        Optional<SeatType> resultSeatType = this.seatTypeRepository.findById(seatType.getId());
        Assertions.assertFalse(resultSeatType.isPresent());
    }

    @Test
    public void deleteAllByEventId()
    {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        this.seatTypeRepository.deleteAllByEventId(seatType.getEvent().getId());
        List<SeatType> list = this.seatTypeRepository.findAllByEventId(seatType.getId());
        Assertions.assertEquals(list.size(), 0);
    }
}
