package com.EventHorizon.EventHorizon.Services.Tickets;

import com.EventHorizon.EventHorizon.DTOs.TicketDto.BuyingAndRefundingDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.SeatTypeRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.TempUserRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.Services.EventServices.EventTypeService;
import com.EventHorizon.EventHorizon.Services.SeatArchive.SeatArchiveOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketTransactionsService {
    @Autowired
    private SeatArchiveOperationService seatArchiveOperationService;
    @Autowired
    private TicketOperationsService ticketOperationsService;
    @Autowired
    private SeatTypeRepositoryService seatTypeRepositoryService;
    @Autowired
    private TempUserRepositoryServiceInterface tempUserRepositoryServiceInterface;
    @Autowired
    private EventTypeService eventTypeService;

    @Transactional
    public void buyTicketCollections(int clientInformationId, List<BuyingAndRefundingDto> list) {
        Client client = tempUserRepositoryServiceInterface.getClientByClientInformationId(clientInformationId);

        for (BuyingAndRefundingDto buyingAndRefundingDto : list)
            buyOneTicketCollection(client, buyingAndRefundingDto);
    }

    private void buyOneTicketCollection(Client client, BuyingAndRefundingDto buyingAndRefundingDto) {
        int seatTypeId = buyingAndRefundingDto.getSeatTypeId();
        int numOfTickets = buyingAndRefundingDto.getNumOfTickets();

        validateEventIsLaunchedAndIsFuture(seatTypeId);

        this.seatArchiveOperationService.removeTickets(seatTypeId, numOfTickets);
        this.ticketOperationsService.addTickets(seatTypeId, client.getId(), numOfTickets);
    }

    public void refundTicket(int clientInformationId, int seatTypeId, int numOfTickets) {
        Client client = tempUserRepositoryServiceInterface.getClientByClientInformationId(clientInformationId);
        validateEventIsLaunchedAndIsFuture(seatTypeId);

        this.ticketOperationsService.removeTickets(seatTypeId, client.getId(), numOfTickets);
        this.seatArchiveOperationService.addTickets(seatTypeId, numOfTickets);
    }

    private void validateEventIsLaunchedAndIsFuture(int seatTypeId) {
        SeatType seatType = seatTypeRepositoryService.getById(seatTypeId);
        Event event = seatType.getEvent();
        eventTypeService.checkAndHandleSeatTypeOfLaunchedFutureEvent(event.getId(), event.getEventType());
    }
}
