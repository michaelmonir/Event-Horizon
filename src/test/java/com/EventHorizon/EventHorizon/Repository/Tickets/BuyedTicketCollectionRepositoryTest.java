package com.EventHorizon.EventHorizon.Repository.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatType.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

@SpringBootTest
public class BuyedTicketCollectionRepositoryTest
{
    @Autowired
    BuyedTicketCollectionRepository buyedTicketCollectionRepository;
    @Autowired
    UserCustomCreator userCustomCreator;
    @Autowired
    SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;

    @Test
    public void saveSuccessfully() {
        Client client = (Client) userCustomCreator.getAndSaveUser(Role.CLIENT);
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();

        BuyedTicketCollection buyedTicketCollection
                = new BuyedTicketCollection(client, seatType, 1);
        Assertions.assertDoesNotThrow(()->this.buyedTicketCollectionRepository.save(buyedTicketCollection));
    }

    @Test
    public void saveWithoutSavingClient() {
        Client client = new Client();
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();

        BuyedTicketCollection buyedTicketCollection
                = new BuyedTicketCollection(client, seatType, 1);
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->this.buyedTicketCollectionRepository.save(buyedTicketCollection));
    }

    @Test
    public void saveWithoutSavingSeatType() {
        Client client = (Client) userCustomCreator.getUser(Role.CLIENT);
        SeatType seatType = new SeatType("a", 1, 1);

        BuyedTicketCollection buyedTicketCollection
                = new BuyedTicketCollection(client, seatType, 1);
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->this.buyedTicketCollectionRepository.save(buyedTicketCollection));
    }

    @Test
    public void saveWithNegativeNumberOfTickets() {
        Client client = (Client) userCustomCreator.getAndSaveUser(Role.CLIENT);
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();

        BuyedTicketCollection buyedTicketCollection
                = new BuyedTicketCollection(client, seatType, -1);
        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.buyedTicketCollectionRepository.save(buyedTicketCollection));
    }

    @Test
    public void getById() {
        Client client = (Client) userCustomCreator.getAndSaveUser(Role.CLIENT);
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();

        BuyedTicketCollection buyedTicketCollection
                = new BuyedTicketCollection(client, seatType, 1);
        this.buyedTicketCollectionRepository.save(buyedTicketCollection);

        Optional<BuyedTicketCollection> resultBuyedTicketCollectionOptional
                = this.buyedTicketCollectionRepository.findByClientIdAndSeatTypeId(client.getId(), seatType.getId());
        Assertions.assertEquals(resultBuyedTicketCollectionOptional.get(), buyedTicketCollection);
    }
}
