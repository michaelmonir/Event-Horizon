package com.EventHorizon.EventHorizon.RepositoryServices.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Exceptions.Tickets.BuyedTicketCollectionNotFoundException;
import com.EventHorizon.EventHorizon.Repository.Tickets.BuyedTicketCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyedTicketCollectionRepositoryService
{
    @Autowired
    BuyedTicketCollectionRepository buyedTicketCollectionRepository;

    public BuyedTicketCollection getBySeatTypeIdAndClientId(int seatTypeId, int clientId) {
        Optional<BuyedTicketCollection> optionalBuyedTicketCollection
                = this.buyedTicketCollectionRepository.findByClientIdAndSeatTypeId(clientId, seatTypeId);
        if (!optionalBuyedTicketCollection.isPresent())
            throw new BuyedTicketCollectionNotFoundException();
        return optionalBuyedTicketCollection.get();
    }

    public BuyedTicketCollection getAndHandleLazyInitialization(SeatType seatType, Client client) {
        Optional<BuyedTicketCollection> optionalBuyedTicketCollection
                = this.buyedTicketCollectionRepository.findByClientIdAndSeatTypeId(client.getId(), seatType.getId());
        if (!optionalBuyedTicketCollection.isPresent()) {
            BuyedTicketCollection buyedTicketCollection = new BuyedTicketCollection(client, seatType, 0);
            this.buyedTicketCollectionRepository.save(buyedTicketCollection);
            return buyedTicketCollection;
        }
        return optionalBuyedTicketCollection.get();
    }

    public void save(BuyedTicketCollection buyedTicketCollection) {
        this.buyedTicketCollectionRepository.save(buyedTicketCollection);
    }
}
