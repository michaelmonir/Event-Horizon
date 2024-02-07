package com.EventHorizon.EventHorizon.Service.Tickets;

import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.User.Client;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatType.SeatTypeCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.EventRepositoryServiceFacadeImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Tickets.BuyedTicketCollectionRepositoryService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicketTransactionWithDatabaseTest
{
    @Autowired
    private TicketTransactionsService ticketTransactionService;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    private EventRepositoryServiceFacadeImpl eventRepositoryServiceFacadeImpl;
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
//
//        Assertions.assertDoesNotThrow(() ->
//                this.ticketTransactionService.buyTicketCollections(this.customClient.getId(), dtoList) );
//        OrganizerSeatArchive organizerSeatArchive = this.organizerSeatArchiveRepositoryService.getBySeatTypeId(this.customOrganizerSeatArchive.getSeatTypeId());
//        Assertions.assertEquals(0, organizerSeatArchive.getAvailable_number_of_seats());
//
//        BuyedTicketCollection buyedTicketCollection = this.buyedTicketCollectionRepositoryService.getBySeatTypeIdAndClientId(this.customSeatType.getId(), this.customClient.getId());
//        Assertions.assertEquals(2, buyedTicketCollection.getNumberOfTickets());
//    }
//
//    @Test
//    public void buyTicketsMoreThanArchiveHas(){
//        this.initializeCustomObjectsForBuying();
//        BuyingAndRefundingDto buyingAndRefundingDto = new BuyingAndRefundingDto(this.customSeatType.getId(), 1);
//
//        List<BuyingAndRefundingDto> listMoreThanItHas = List.of(buyingAndRefundingDto, buyingAndRefundingDto, buyingAndRefundingDto);
//
//        Assertions.assertThrows(AvailableTicketsIsLessThanRequiredToBuyException.class, () ->
//                this.ticketTransactionService.buyTicketCollections(this.customClient.getId(), listMoreThanItHas));
//        OrganizerSeatArchive organizerSeatArchive = this.organizerSeatArchiveRepositoryService.getBySeatTypeId(this.customOrganizerSeatArchive.getSeatTypeId());
//        Assertions.assertEquals(2, organizerSeatArchive.getAvailable_number_of_seats());
//
//        BuyedTicketCollection buyedTicketCollection = this.buyedTicketCollectionRepositoryService.getBySeatTypeIdAndClientId(this.customSeatType.getId(), this.customClient.getId());
//        Assertions.assertEquals(0, buyedTicketCollection.getNumberOfTickets());
//    }
//
//    @Test
//    public void refundTicketSuccessful(){
//        this.initializeCustomObjectsForRefunding();
//        BuyingAndRefundingDto buyingAndRefundingDto = new BuyingAndRefundingDto(this.customSeatType.getId(), 1);
//
//        List<BuyingAndRefundingDto> dtoList = List.of(buyingAndRefundingDto, buyingAndRefundingDto);
//
//        Assertions.assertDoesNotThrow(() -> this.ticketTransactionService.refundTicketCollections(this.customClient.getId(), dtoList));
//        OrganizerSeatArchive organizerSeatArchive = this.organizerSeatArchiveRepositoryService.getBySeatTypeId(this.customOrganizerSeatArchive.getSeatTypeId());
//        Assertions.assertEquals(2, organizerSeatArchive.getAvailable_number_of_seats());
//
//        BuyedTicketCollection buyedTicketCollection = this.buyedTicketCollectionRepositoryService.getBySeatTypeIdAndClientId(this.customSeatType.getId(), this.customClient.getId());
//        Assertions.assertEquals(0, buyedTicketCollection.getNumberOfTickets());
//    }
//
//    @Test
//    public void refundTicketMoreThanItHas(){
//        this.initializeCustomObjectsForRefunding();
//        BuyingAndRefundingDto buyingAndRefundingDto = new BuyingAndRefundingDto(this.customSeatType.getId(), 1);
//
//        List<BuyingAndRefundingDto> listMoreThanItHas = List.of(buyingAndRefundingDto, buyingAndRefundingDto, buyingAndRefundingDto);
//
//        Assertions.assertThrows(BuyedTicketsIslessThanRequiredToRefund.class, () ->
//                this.ticketTransactionService.refundTicketCollections(
//                        this.customClient.getId(), listMoreThanItHas));
//        OrganizerSeatArchive organizerSeatArchive = this.organizerSeatArchiveRepositoryService.getBySeatTypeId(this.customOrganizerSeatArchive.getSeatTypeId());
//        Assertions.assertEquals(0, organizerSeatArchive.getAvailable_number_of_seats());
//
//        BuyedTicketCollection buyedTicketCollection = this.buyedTicketCollectionRepositoryService.getBySeatTypeIdAndClientId(this.customSeatType.getId(), this.customClient.getId());
//        Assertions.assertEquals(2, buyedTicketCollection.getNumberOfTickets());
//    }

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
