package com.EventHorizon.EventHorizon.RepositoryServices.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.Tickets.BuyedTicketCollectionNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BuyedTicketCollectionWithDatabaseTest
{
    @Autowired
    BuyedTicketCollectionRepositoryService TicketRepositoryService;
    @Autowired
    private UserCustomCreator userCustomCreator;
    @Autowired
    private SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;

    private BuyedTicketCollection customBuyedTicketCollection;
    private SeatType customSeatType;
    private Client customClient;

    @Test
    public void save() {
        Assertions.assertDoesNotThrow( () -> this.initializeAndSaveCustomObjectsInDB());
    }

    @Test
    public void saveThenEditThenSave() {
        this.initializeAndSaveCustomObjectsInDB();
        this.customBuyedTicketCollection.setNumberOfTickets(50);
        this.TicketRepositoryService.save(this.customBuyedTicketCollection);
    }

    @Test
    public void getByClientIdAndSeatTypeId() {
        this.initializeAndSaveCustomObjectsInDB();

        BuyedTicketCollection result
                = this.TicketRepositoryService.getBySeatTypeIdAndClientId(customSeatType.getId(), customClient.getId());
        Assertions.assertEquals(this.customBuyedTicketCollection, result);
    }

    @Test
    public void getByClientIdAndSeatTypeIdNotFound() {
        this.initializeAndSaveCustomObjectsInDB();
        int wrongClientId = this.customClient.getId() + 1;
        Assertions.assertThrows(BuyedTicketCollectionNotFoundException.class, () ->
                this.TicketRepositoryService
                        .getBySeatTypeIdAndClientId(wrongClientId, customSeatType.getId()) );
    }

    public void initializeAndSaveCustomObjectsInDB() {
        this.customClient = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
        this.customSeatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        this.customBuyedTicketCollection = new BuyedTicketCollection(this.customClient, this.customSeatType, 1);
        this.TicketRepositoryService.save(this.customBuyedTicketCollection);
    }
}











