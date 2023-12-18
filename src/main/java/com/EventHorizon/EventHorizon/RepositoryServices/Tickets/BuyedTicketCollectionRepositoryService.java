package com.EventHorizon.EventHorizon.RepositoryServices.Tickets;

import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
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

    public void save(BuyedTicketCollection buyedTicketCollection) {

        try{
            this.getBySeatTypeIdAndClientId(
                    buyedTicketCollection.getSeatType().getId(),
                    buyedTicketCollection.getClient().getId());
            this.buyedTicketCollectionRepository.update(buyedTicketCollection);
        }
        catch (BuyedTicketCollectionNotFoundException e)
        {
            this.buyedTicketCollectionRepository.create(buyedTicketCollection);
        }
    }
}
