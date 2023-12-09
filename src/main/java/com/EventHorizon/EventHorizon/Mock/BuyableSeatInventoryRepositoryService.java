package com.EventHorizon.EventHorizon.Mock;

import org.springframework.stereotype.Service;

@Service
public class BuyableSeatInventoryRepositoryService {
    public void update(BuyableSeatInventory buyableSeatInventory) {
    }

    public BuyableSeatInventory find(int seatTypeId) {
        return new BuyableSeatInventory();
    }

    public void saveSeatInventory(int seatTypeId, int numOfTickets) {
    }
}
