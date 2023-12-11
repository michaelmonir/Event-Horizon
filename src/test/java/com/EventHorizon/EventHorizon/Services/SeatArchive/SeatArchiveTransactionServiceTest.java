package com.EventHorizon.EventHorizon.Services.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Exceptions.Ticket.AvailableTicketsIsLessThanRequiredToBuy;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SeatArchiveTransactionServiceTest
{
    @InjectMocks
    SeatArchiveTransactionService seatArchiveTransactionService;
    @Mock
    OrganizerSeatArchiveRepositoryService organizerSeatArchiveRepositoryService;

    private SeatType customSeatType;
    private OrganizerSeatArchive customArchiveWithTickets;
    private OrganizerSeatArchive customArchiveWithoutTickets;

    @Test
    public void removeTicketsSuccessful(){
        this.initializeCustomObjects();
        when(organizerSeatArchiveRepositoryService.getBySeatTypeId(any(int.class)))
                .thenReturn(customArchiveWithTickets);

        this.seatArchiveTransactionService.removeTickets(this.customSeatType.getId(), 1);

        verify(this.organizerSeatArchiveRepositoryService, Mockito.times(1))
                .update(this.customArchiveWithoutTickets);
    }

    @Test
    public void removeTicketsMoreTicketsThatItHas(){
        this.initializeCustomObjects();
        when(organizerSeatArchiveRepositoryService.getBySeatTypeId(any(int.class)))
                .thenReturn(customArchiveWithTickets);

        Assertions.assertThrows(AvailableTicketsIsLessThanRequiredToBuy.class, () ->
                this.seatArchiveTransactionService.removeTickets(this.customSeatType.getId(), 2));
    }

    @Test
    public void addTicketsSuccessful(){
        this.initializeCustomObjects();
        when(organizerSeatArchiveRepositoryService.getBySeatTypeId(any(int.class)))
                .thenReturn(customArchiveWithoutTickets);

        this.seatArchiveTransactionService.addTickets(this.customSeatType.getId(), 1);

        verify(this.organizerSeatArchiveRepositoryService, Mockito.times(1))
                .update(this.customArchiveWithTickets);
    }

    private void initializeCustomObjects(){
        this.customSeatType = new SeatType("a", 1);
        this.customArchiveWithTickets = new OrganizerSeatArchive(this.customSeatType, 1, 1);
        this.customArchiveWithoutTickets = new OrganizerSeatArchive(this.customSeatType, 1, 0);
    }
}
