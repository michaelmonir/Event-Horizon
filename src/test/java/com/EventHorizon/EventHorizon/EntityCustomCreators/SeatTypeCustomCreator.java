package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatTypeCustomCreator
{
    int numberOfCreatedObjects = 0;

    public SeatType getSeatType()
    {
        numberOfCreatedObjects++;
        SeatType seatType = new SeatType("Seat Type" + numberOfCreatedObjects, numberOfCreatedObjects, numberOfCreatedObjects);

        return seatType;
    }
}
