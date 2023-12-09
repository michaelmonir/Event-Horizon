package com.EventHorizon.EventHorizon.Mock;

import org.springframework.stereotype.Service;

@Service
public class BuyedTicketCollectionRepositoryService {
    public void update(BuyedTicketCollection buyedTicketCollection) {
    }

    public BuyedTicketCollection find(int clientId, int seatTypeId) {
        return new BuyedTicketCollection();
    }

    public void saveTickets(int clientId, int seatTypeId, int numOfTickets) {
    }
}
