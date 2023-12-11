package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.ClientInformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.SeatTypeRepositoryService;
import com.EventHorizon.EventHorizon.Services.SeatArchive.SeatArchiveTransactionService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketsTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    SeatArchiveTransactionService seatArchiveTransactionService;
    @Autowired
    TicketsTransactionService ticketsTransactionService;
    @Autowired
    SeatTypeRepositoryService seatTypeRepositoryService;
    @Autowired
    InformationRepositoryService informationRepositoryService;
    @Autowired
    ClientInformationRepositoryService clientInformationRepositoryService;

    public void buyTicket(int clientInformationId, int seatTypeId, int numOfTickets) {
        Client client = this.getClientByClientInformationId(clientInformationId);
        validateEventIsLaunchedAndIsFuture(seatTypeId);

        this.seatArchiveTransactionService.removeTickets(seatTypeId, numOfTickets);
        this.ticketsTransactionService.addTickets(seatTypeId, client.getId(), numOfTickets);
    }

    public void refundTicket(int clientInformationId, int seatTypeId, int numOfTickets) {
        Client client = this.getClientByClientInformationId(clientInformationId);
        validateEventIsLaunchedAndIsFuture(seatTypeId);

        this.ticketsTransactionService.removeTickets(seatTypeId, client.getId(), numOfTickets);
        this.seatArchiveTransactionService.addTickets(seatTypeId, numOfTickets);
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
