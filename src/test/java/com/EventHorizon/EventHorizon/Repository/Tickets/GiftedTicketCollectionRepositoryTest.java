package com.EventHorizon.EventHorizon.Repository.Tickets;

import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GiftedTicketCollectionRepositoryTest
{
//    @Autowired
//    GiftedTicketCollectionRepository giftedTicketCollectionRepository;
    @Autowired
    UserCustomCreator userCustomCreator;
    @Autowired
    SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;

//    @Test
//    public void saveSuccessfully() {
//
//        Client client = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
//        Sponsor sponsor = (Sponsor)this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);
//        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
//
//        GiftedTicketCollection giftedTicketCollection
//                = new GiftedTicketCollection(client, seatType, sponsor, 1);
//        Assertions.assertDoesNotThrow(()->this.giftedTicketCollectionRepository.save(giftedTicketCollection));
//    }

//    @Test
//    public void saveWithoutSavingClient() {
//
//        Client client = new Client();
//        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
//
//        BuyedTicketCollection buyedTicketCollection
//                = new BuyedTicketCollection(client, seatType, new Date(), 1);
//        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
//                ()->this.buyedTicketCollectionRepository.save(buyedTicketCollection));
//    }
//
//    @Test
//    public void saveWithoutSavingSeatType() {
//
//        Client client = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
//        SeatType seatType = new SeatType("a", 1);
//
//        BuyedTicketCollection buyedTicketCollection
//                = new BuyedTicketCollection(client, seatType, new Date(), 1);
//        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
//                ()->this.buyedTicketCollectionRepository.save(buyedTicketCollection));
//    }
//
//    @Test
//    public void saveWithNegativeNumberOfTickets() {
//
//        Client client = (Client)this.userCustomCreator.getUserAndSaveInRepository(Role.CLIENT);
//        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
//
//        BuyedTicketCollection buyedTicketCollection
//                = new BuyedTicketCollection(client, seatType, new Date(), -1);
//        Assertions.assertThrows(DataIntegrityViolationException.class,
//                ()->this.buyedTicketCollectionRepository.save(buyedTicketCollection));
//    }
//
//    @Test
//    public void getById() {
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
