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

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO buyed_ticket_collection (client_id, seat_type_id, number_of_tickets) " +
            "VALUES (:#{#buyedTicketCollection.client.id}, :#{#buyedTicketCollection.seatType.id}, :#{#buyedTicketCollection.numberOfTickets})", nativeQuery = true)
    void create(@Param("buyedTicketCollection") BuyedTicketCollection buyedTicketCollection);

    // Custom native SQL query for updating an existing record
    @Modifying
    @Transactional
    @Query(value = "UPDATE buyed_ticket_collection " +
            "SET number_of_tickets = :#{#buyedTicketCollection.numberOfTickets} " +
            "WHERE client_id = :#{#buyedTicketCollection.client.id} AND seat_type_id = :#{#buyedTicketCollection.seatType.id}", nativeQuery = true)
    void update(@Param("buyedTicketCollection") BuyedTicketCollection buyedTicketCollection);
}
