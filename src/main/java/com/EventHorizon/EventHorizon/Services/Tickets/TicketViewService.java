package com.EventHorizon.EventHorizon.Services.Tickets;

import com.EventHorizon.EventHorizon.DTOs.TicketViewDto;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.OrganizerSeatArchiveRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.SeatTypeRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Tickets.BuyedTicketCollectionRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketViewService {
    @Autowired
    private SeatTypeRepositoryService seatTypeRepositoryService;
    @Autowired
    private OrganizerSeatArchiveRepositoryService osaRepositoryService;
    @Autowired
    private BuyedTicketCollectionRepositoryService btcRepositoryService;

    public List<TicketViewDto> getTicketsDtoList(int userInformationId, int eventId) {
        List<SeatType> seatTypeList = seatTypeRepositoryService.getAllByEventId(eventId);
        List<TicketViewDto> list = new ArrayList<>();
        for (SeatType seatType : seatTypeList) {
            OrganizerSeatArchive organizerSeatArchive = osaRepositoryService.getBySeatTypeId(seatType.getId());
            TicketViewDto ticketViewDto = this.TicketViewDtoFromSeatTypeAndOrganizerSeatArchive(seatType, organizerSeatArchive);
            ticketViewDto.setNumberOfBuyedTickets(this.setNumberOfBuyedTickets(userInformationId, seatType.getId()));
            list.add(ticketViewDto);
        }
        return list;
    }

    private TicketViewDto TicketViewDtoFromSeatTypeAndOrganizerSeatArchive(SeatType seatType, OrganizerSeatArchive organizerSeatArchive) {
        return TicketViewDto.builder()
                .seatTypeId(seatType.getId())
                .name(seatType.getName())
                .totalNumberOfSeats(organizerSeatArchive.getTotal_number_of_seats())
                .availableNumberOfSeats(organizerSeatArchive.getAvailable_number_of_seats())
                .price(seatType.getPrice())
                .build();
    }

    private int setNumberOfBuyedTickets(int id, int seatTypeId) {
        try {
            BuyedTicketCollection buyedTicketCollection
                    = btcRepositoryService.getBySeatTypeIdAndClientId(seatTypeId, id);
            return buyedTicketCollection.getNumberOfTickets();
        } catch (Exception e) {
            // not initialized due to not buying tickets
            return 0;
        }
    }
}