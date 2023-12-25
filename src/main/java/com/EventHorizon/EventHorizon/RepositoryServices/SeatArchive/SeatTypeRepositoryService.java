package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Exceptions.SeatArchive.SeatTypeNotFoundException;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatTypeRepositoryService
{
    @Autowired
    SeatTypeRepository seatTypeRepository;

    public SeatType getById(int id)
    {
        Optional<SeatType> seatTypeOptional = seatTypeRepository.findById(id);
        if (!seatTypeOptional.isPresent())
            throw new SeatTypeNotFoundException();
        return seatTypeOptional.get();
    }

    public void deleteAllByEventId(int eventId)
    {
        this.seatTypeRepository.deleteAllByEventId(eventId);
    }
}
