package com.EventHorizon.EventHorizon.Services.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.AvailableTicketsIsLessThanRequiredToBuyException;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatArchiveOperationService
{
    @Autowired
    OrganizerSeatArchiveRepositoryService organizerSeatArchiveRepositoryService;

    public void removeTickets(int seatTypeId, int numOfTickets) {
        OrganizerSeatArchive organizerSeatArchive
                = organizerSeatArchiveRepositoryService.getBySeatTypeId(seatTypeId);

        int oldNumberOfTickets = organizerSeatArchive.getAvailable_number_of_seats();

        if (oldNumberOfTickets < numOfTickets)
            throw new AvailableTicketsIsLessThanRequiredToBuyException();

        organizerSeatArchive.setAvailable_number_of_seats(oldNumberOfTickets - numOfTickets);
        organizerSeatArchiveRepositoryService.save(organizerSeatArchive);
    }

    public void addTickets(int seatTypeId, int numOfTickets) {
        OrganizerSeatArchive organizerSeatArchive
                = organizerSeatArchiveRepositoryService.getBySeatTypeId(seatTypeId);

        int oldNumbeOfTickets = organizerSeatArchive.getAvailable_number_of_seats();

        organizerSeatArchive.setAvailable_number_of_seats(oldNumbeOfTickets + numOfTickets);
        organizerSeatArchiveRepositoryService.save(organizerSeatArchive);
    }
}
