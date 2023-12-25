package com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Exceptions.SeatArchive.OrganizerSeatArchiveNotFoundException;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.OrganizerSeatArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void save(OrganizerSeatArchive organizerSeatArchive) {
        this.organizerSeatArchiveRepository.save(organizerSeatArchive);
    }

    public List<OrganizerSeatArchive> getAllByEventId(int eventId) {
        return this.organizerSeatArchiveRepository.findAllByEventId(eventId);
    }
}
