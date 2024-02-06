package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.DTOs.TicketDto.BuyingAndRefundingDto;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.User.Client;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatType.SeatTypeCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Repository.Views.ClientGoingViewRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Tickets.BuyedTicketCollectionRepositoryService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketTransactionsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class test {

    @Autowired
    private ClientGoingViewRepository clientGoingViewRepository;
    @Autowired
    private TicketTransactionsService ticketTransactionService;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    private OrganizerSeatArchiveRepositoryService organizerSeatArchiveRepositoryService;
    @Autowired
    private UserCustomCreator userCustomCreator;
    @Autowired
    private BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;

    private BuyedTicketCollection customTicketCollection;
    private Client customClient;
    private LaunchedEvent customEvent;
    private SeatType customSeatType;
    private OrganizerSeatArchive customOrganizerSeatArchive;



//    @Test
//    public void buyTicketsSuccessful(){
//        this.initializeCustomObjectsForBuying();
//        BuyingAndRefundingDto buyingAndRefundingDto = new BuyingAndRefundingDto(this.customSeatType.getId(), 1);
//
//        List<BuyingAndRefundingDto> dtoList = List.of(buyingAndRefundingDto, buyingAndRefundingDto);
//        this.ticketTransactionService.buyTicketCollections(this.customClient.getId(), dtoList);
//
////        List<ClientGoingView> clientGoingViewList = this.clientGoingViewRepository.findAll();
////        Assertions.assertEquals(1, clientGoingViewList.size());
////        Assertions.assertEquals(this.customClient.getId(), clientGoingViewList.get(0).getClient_id());
//    }
//
//    private void initializeCustomObjectsForBuying(){
//        this.initializeCustomEventObjects();
//
//        this.customOrganizerSeatArchive = new OrganizerSeatArchive(this.customSeatType, 2, 2);
//        this.organizerSeatArchiveRepositoryService.save(this.customOrganizerSeatArchive);
//
//        this.customTicketCollection = new BuyedTicketCollection(customClient, customSeatType, 0);
//        this.buyedTicketCollectionRepositoryService.save(this.customTicketCollection);
//    }
//
//    private void initializeCustomObjectsForRefunding(){
//        this.initializeCustomEventObjects();
//
//        this.customOrganizerSeatArchive = new OrganizerSeatArchive(this.customSeatType, 2, 0);
//        this.organizerSeatArchiveRepositoryService.save(this.customOrganizerSeatArchive);
//
//        this.customTicketCollection = new BuyedTicketCollection(customClient, customSeatType, 2);
//        this.buyedTicketCollectionRepositoryService.save(this.customTicketCollection);
//    }
//
//    private void initializeCustomEventObjects() {
//        this.customClient = (Client)userCustomCreator.getAndSaveUser(Role.CLIENT);
//
//        this.customSeatType = this.seatTypeCustomCreator.getSeatType();
//
//        this.customEvent = this.eventCustomCreator.getLaunchedEvent();
//        this.customEvent.setSeatTypes(List.of(this.customSeatType));
//        this.eventRepositoryServiceInterface.create(this.customEvent);
//    }
}

