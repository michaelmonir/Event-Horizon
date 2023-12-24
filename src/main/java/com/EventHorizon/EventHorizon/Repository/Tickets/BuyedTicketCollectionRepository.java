package com.EventHorizon.EventHorizon.Repository.Tickets;

import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BuyedTicketCollectionRepository extends JpaRepository<BuyedTicketCollection, Integer>
{
    Optional<BuyedTicketCollection> findByClientIdAndSeatTypeId(int clientId, int seatTypeId);
}
