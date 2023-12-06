package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.stereotype.Service;

@Service
public class SeatTypeCustomCreator
{
    int numberOfCreatedObjects = 0;

    public SeatType getSeatType()
    {
        numberOfCreatedObjects++;
        SeatType seatType = new SeatType("Seat Type" + numberOfCreatedObjects, numberOfCreatedObjects);

        return seatType;
    }
}
