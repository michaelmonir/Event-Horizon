package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Exceptions.SeatArchive.OrganizerSeatArchiveNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.SeatArchive.OrganizerSeatArchiveShouldHaveIdWhileCreatingException;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.OrganizerSeatArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerSeatArchiveRepositoryService
{
    @Autowired
    OrganizerSeatArchiveRepository organizerSeatArchiveRepository;

    public OrganizerSeatArchive getBySeatTypeId(int seatTypeId) {
        Optional<OrganizerSeatArchive> optionalOrganizerSeatArchive
                = this.organizerSeatArchiveRepository.findBySeatTypeId(seatTypeId);
        if (!optionalOrganizerSeatArchive.isPresent())
            throw new OrganizerSeatArchiveNotFoundException();
        return optionalOrganizerSeatArchive.get();
    }

    public void saveWhenCreating(OrganizerSeatArchive organizerSeatArchive) {
        if (organizerSeatArchive.getSeatTypeId() != 0)
            throw new OrganizerSeatArchiveShouldHaveIdWhileCreatingException();
        this.organizerSeatArchiveRepository.save(organizerSeatArchive);
    }

    public OrganizerSeatArchive update(OrganizerSeatArchive organizerSeatArchive) {
        this.getBySeatTypeId(organizerSeatArchive.getSeatTypeId());
        return this.organizerSeatArchiveRepository.save(organizerSeatArchive);
    }
}
