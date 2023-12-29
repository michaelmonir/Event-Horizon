package com.EventHorizon.EventHorizon.Repository.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.GiftedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest
public class GiftedTicketCollectionRepositoryTest
{
    @Autowired
    GiftedTicketCollectionRepository giftedTicketCollectionRepository;
    @Autowired
    UserCustomCreator userCustomCreator;
    @Autowired
    SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;

    @Test
    public void saveSuccessfully() {
        Client client = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
        Sponsor sponsor = (Sponsor)this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();

        GiftedTicketCollection giftedTicketCollection
                = new GiftedTicketCollection(client, seatType, sponsor, 1);
        Assertions.assertDoesNotThrow(()->this.giftedTicketCollectionRepository.save(giftedTicketCollection));
    }

    @Test
    public void saveWithoutSavingClient() {
        Client client = new Client();
        Sponsor sponsor = (Sponsor)this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();

        GiftedTicketCollection buyedTicketCollection
                = new GiftedTicketCollection(client, seatType, sponsor, 1);
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->this.giftedTicketCollectionRepository.save(buyedTicketCollection));
    }

    @Test
    public void saveWithoutSavingSponsor() {
        Client client = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
        Sponsor sponsor = new Sponsor();
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();

        GiftedTicketCollection giftedTicketCollection
                = new GiftedTicketCollection(client, seatType, sponsor, 1);
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->this.giftedTicketCollectionRepository.save(giftedTicketCollection));
    }

    @Test
    public void saveWithNegativeNumberOfTickets() {
        Client client = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);
        SeatType seatType = new SeatType("name", 1, 1);

        GiftedTicketCollection giftedTicketCollection
                = new GiftedTicketCollection(client, seatType, sponsor, 1);
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->this.giftedTicketCollectionRepository.save(giftedTicketCollection));
    }
//
//    @Test
//    public void getByIdAndHandleNotFound() {
//        Client client = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
//        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
//
//        BuyedTicketCollection buyedTicketCollection
//                = new BuyedTicketCollection(client, seatType, new Date(), 1);
//        this.buyedTicketCollectionRepository.save(buyedTicketCollection);
//
//        Optional<BuyedTicketCollection> resultBuyedTicketCollectionOptional
//                = this.buyedTicketCollectionRepository.findByClientIdAndSeatTypeId(client.getId(), seatType.getId());
//        Assertions.assertEquals(resultBuyedTicketCollectionOptional.get(), buyedTicketCollection);
//    }
}
