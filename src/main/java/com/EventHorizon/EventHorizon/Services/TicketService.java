//package com.EventHorizon.EventHorizon.Services;
//
//import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
//import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
//import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
//import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
//import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.ClientInformationRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TicketService {
//    @Autowired
//    private LaunchedEventRepositoryService launchedEventRepositoryService;
//    @Autowired
//    private ClientInformationRepositoryService clientInformationService;
//    @Autowired
//    private InformationRepositoryService informationService;
//    public void buyTicket(int clientId,int launchedEventId,int seatTypeId,int numOfTickets){
//        LaunchedEvent launchedEvent=launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId);
//        //
//        BuyableSeatInventory buyableSeatInventory=buyableSeatInventoryRepositoryService.getBuyableSeatInventoryByID(seatTypeId);
//        List<BuyableTicket> tickets=buyableSeatInventory.getTickets(numOfTickets);
//        //num
//        3 7 //gene(no ,id)
//        Client client=getClientFromInformationId(clientId);
//        client.addTickets(tickets);
//        informationService.update(clientId,client.getInformation());
//    }
//    public void refundTicket(int clientId,int launchedEventId,int seatTypeId,int numOfTickets){
//        LaunchedEvent launchedEvent=launchedEventRepositoryService.getEventAndHandleNotFound(launchedEventId);
//        BuyableSeatInventory buyableSeatInventory=buyableSeatInventoryRepositoryService.getBuyableSeatInventoryByID(launchedEvent,seatTypeId);
//        List<BuyableTicket> tickets=buyableSeatInventory.addTickets(numOfTickets);
//        Client client=getClientFromInformationId(clientId);
//        client.removeTickets(tickets);
//        refund(ticket,)
//        informationService.update(clientId,client.getInformation());
//    }
//    public Client getClientFromInformationId(int clientId) {
//        Information information = informationService.getByID(clientId);
//
//        return (Client) clientInformationService.getUserByInformation(information);
//    }
//}
