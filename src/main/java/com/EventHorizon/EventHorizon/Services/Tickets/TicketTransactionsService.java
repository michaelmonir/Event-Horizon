package com.EventHorizon.EventHorizon.Services.Tickets;

import com.EventHorizon.EventHorizon.DTOs.TicketDto.BuyingAndRefundingDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.ClientInformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.SeatTypeRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.TempUserRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.Services.SeatArchive.EventTypeService;
import com.EventHorizon.EventHorizon.Services.SeatArchive.SeatArchiveOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketTransactionsService {
    @Autowired
    SeatArchiveOperationService seatArchiveOperationService;
    @Autowired
    TicketOperationsService ticketOperationsService;
    @Autowired
    SeatTypeRepositoryService seatTypeRepositoryService;
    @Autowired
    TempUserRepositoryServiceInterface tempUserRepositoryServiceInterface;
    @Autowired
    EventTypeService eventTypeService;

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
        if (!eventTypeService.checkSeatTypeOfLaunchedFutureEvent(event.getId(), event.getEventType()))
            throw new NotLaunchedEventException();

//        Event event = seatType.getEvent();
//
//        if (event.getEventType() != EventType.LAUNCHEDEVENT)
//            throw new NotLaunchedEventException();
//
//        FutureEventWrapper eventWrapper = new FutureEventWrapper((LaunchedEvent) event);
    }
}
