package com.EventHorizon.EventHorizon.RepositoryServices.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Exceptions.Tickets.BuyedTicketCollectionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.Tickets.BuyedTicketCollectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class BuyedTicketCollectionTest
{
    @InjectMocks
    BuyedTicketCollectionRepositoryService buyedTicketCollectionRepositoryService;
    @Mock
    BuyedTicketCollectionRepository buyedTicketCollectionRepository;

    private BuyedTicketCollection customBuyedTicketCollection;
    private SeatType customSeatType;
    private Client customClient;


    @Test
    public void getOrganizerArchiveByClientIdAndSeatTypeId() {
        this.initializeCustomObjectsAndMocks();
        Mockito.when(this.buyedTicketCollectionRepository
                        .findByClientIdAndSeatTypeId(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(this.customBuyedTicketCollection));

        BuyedTicketCollection result
                = this.buyedTicketCollectionRepositoryService
                .getBySeatTypeIdAndClientId(customClient.getId(), customSeatType.getId());
        Assertions.assertEquals(this.customBuyedTicketCollection, result);
    }

    @Test
    public void getOrganizerArchiveByClientIdAndSeatTypeIdNotFound() {
        this.initializeCustomObjectsAndMocks();
        Mockito.when(this.buyedTicketCollectionRepository
                        .findByClientIdAndSeatTypeId(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(BuyedTicketCollectionNotFoundException.class, () ->
                this.buyedTicketCollectionRepositoryService
                .getBySeatTypeIdAndClientId(customClient.getId(), customSeatType.getId()) );
    }

    @Test
    public void saveBuyedTicketCollection() {
        Assertions.assertDoesNotThrow(() ->
                this.buyedTicketCollectionRepositoryService
                        .save(this.customBuyedTicketCollection));
    }

    public void initializeCustomObjectsAndMocks() {
        this.customClient = new Client();
        this.customSeatType = new SeatType("a", 1);
        this.customBuyedTicketCollection = new BuyedTicketCollection(this.customClient, this.customSeatType, 1);

        Mockito.when(this.buyedTicketCollectionRepository
                        .findByClientIdAndSeatTypeId(Mockito.any(Integer.class), Mockito.any(Integer.class)))
                .thenReturn(Optional.ofNullable(this.customBuyedTicketCollection));
    }
}
