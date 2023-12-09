package com.EventHorizon.EventHorizon.Repository.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.Tickets.GiftedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftedTicketCollectionRepository extends JpaRepository<BuyedTicketCollection, Integer>
{
    GiftedTicketCollection save(GiftedTicketCollection giftedTicketCollection);

//    GiftedTicketCollection findByClientAndSeatTypeAndSponsor(Client client, SeatType seatType, Sponsor sponsor);
//    Optional<GiftedTicketCollection> findByClientIdAndSeatTypeIdAndSponsorId(int clientId, int seatTypeId, int sponsorId);
//    Optional<GiftedTicketCollection> findByClientAndSeatTypeAndSponsor(Client client, SeatType seatType, Sponsor sponsor);

}
