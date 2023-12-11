package com.EventHorizon.EventHorizon.Services.Tickets;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.ClientInformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.SeatTypeRepositoryService;
import com.EventHorizon.EventHorizon.Services.SeatArchive.SeatArchiveOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketTransactionsService {
    @Autowired
    SeatArchiveOperationService seatArchiveOperationService;
    @Autowired
    TicketOperationsService ticketOperationsService;
    @Autowired
    SeatTypeRepositoryService seatTypeRepositoryService;
    @Autowired
    InformationRepositoryService informationRepositoryService;
    @Autowired
    ClientInformationRepositoryService clientInformationRepositoryService;

    public void buyTicket(int clientInformationId, int seatTypeId, int numOfTickets) {
        Client client = this.getClientByClientInformationId(clientInformationId);
        validateEventIsLaunchedAndIsFuture(seatTypeId);

        this.seatArchiveOperationService.removeTickets(seatTypeId, numOfTickets);
        this.ticketOperationsService.addTickets(seatTypeId, client.getId(), numOfTickets);
    }
    public void refundTicket(int clientInformationId, int seatTypeId, int numOfTickets) {
        Client client = this.getClientByClientInformationId(clientInformationId);
        validateEventIsLaunchedAndIsFuture(seatTypeId);

        this.ticketOperationsService.removeTickets(seatTypeId, client.getId(), numOfTickets);
        this.seatArchiveOperationService.addTickets(seatTypeId, numOfTickets);
    }
    private void validateEventIsLaunchedAndIsFuture(int seatTypeId) {
        SeatType seatType = seatTypeRepositoryService.getById(seatTypeId);

        Event event = seatType.getEvent();
        if (!(event instanceof LaunchedEvent))
            throw new NotLaunchedEventException();

        FutureEventWrapper eventWrapper = new FutureEventWrapper((LaunchedEvent) event);
    }

    private Client getClientByClientInformationId(int clientInformationId) {
        Information information = this.informationRepositoryService.getByID(clientInformationId);
        return (Client)this.clientInformationRepositoryService.getUserByInformation(information);
    }
}
