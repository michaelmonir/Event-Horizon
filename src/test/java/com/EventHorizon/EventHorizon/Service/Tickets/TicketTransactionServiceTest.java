package com.EventHorizon.EventHorizon.Service.Tickets;

import com.EventHorizon.EventHorizon.DTOs.TicketDto.BuyingAndRefundingDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCollection;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.SeatArchive.SeatTypeRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.TempUserRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.Services.EventServices.EventTypeService;
import com.EventHorizon.EventHorizon.Services.SeatArchive.SeatArchiveOperationService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketOperationsService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketTransactionsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TicketTransactionServiceTest {

    @InjectMocks
    private TicketTransactionsService ticketTransactionService;
    @Mock
    private TicketOperationsService ticketOperationsService;
    @Mock
    private SeatArchiveOperationService seatArchiveOperationService;
    @Mock
    private TempUserRepositoryServiceInterface tempUserRepositoryServiceInterface;
    @Mock
    private SeatTypeRepositoryService seatTypeRepositoryService;
    @Mock
    private EventTypeService eventTypeService;

    private BuyedTicketCollection customTicketCollection;
    private Client customClient;
    private SeatType customSeatType;
    private BuyingAndRefundingDto customBuyingAndRefundingDto;
    private List<BuyingAndRefundingDto> customBuyingAndRefundingDtoList;


    @Test
    public void buyOneTicketSuccessful(){
        this.initializeCustomObjects();

        when(tempUserRepositoryServiceInterface.getClientByClientInformationId(anyInt())).thenReturn(this.customClient);
        when(seatTypeRepositoryService.getById(anyInt())).thenReturn(this.customSeatType);

        this.ticketTransactionService.buyTicketCollections(1, this.customBuyingAndRefundingDtoList);
    }

    private void initializeCustomObjects(){
        this.customClient = Client.builder().id(1).build();
        this.customSeatType = new SeatType(5, new LaunchedEvent(), "name", 1);
        this.customTicketCollection = new BuyedTicketCollection(customClient, customSeatType, 1);

        this.customBuyingAndRefundingDto = new BuyingAndRefundingDto(1, 1);
        this.customBuyingAndRefundingDtoList = List.of(customBuyingAndRefundingDto);
    }
}
