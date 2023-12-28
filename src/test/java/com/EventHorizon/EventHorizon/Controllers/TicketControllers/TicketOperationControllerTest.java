package com.EventHorizon.EventHorizon.Controllers.TicketControllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.EventHorizon.EventHorizon.DTOs.TicketViewDto;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketTransactionsService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketViewService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TicketOperationController.class})
@ExtendWith(SpringExtension.class)
class TicketOperationControllerTest {
    @Autowired
    private TicketOperationController ticketOperationController;

    @MockBean
    private TicketTransactionsService ticketTransactionsService;

    @MockBean
    private TicketViewService ticketViewService;



    @Test
    void testGetAllTickets() throws Exception {
        // Arrange
        when(ticketViewService.getTicketsDtoList(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/ticket/getAllTickets/{clientInformationId}/{eventId}", 1, 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ticketOperationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetAllTickets2() throws Exception {
        // Arrange
        ArrayList<TicketViewDto> ticketViewDtoList = new ArrayList<>();
        TicketViewDto buildResult = TicketViewDto.builder()
                .availableNumberOfSeats(10)
                .name("Name")
                .numberOfBuyedTickets(10)
                .price(1)
                .seatTypeId(1)
                .totalNumberOfSeats(10)
                .build();
        ticketViewDtoList.add(buildResult);
        when(ticketViewService.getTicketsDtoList(anyInt(), anyInt())).thenReturn(ticketViewDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/ticket/getAllTickets/{clientInformationId}/{eventId}", 1, 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ticketOperationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"seatTypeId\":1,\"name\":\"Name\",\"totalNumberOfSeats\":10,\"availableNumberOfSeats\":10,\"price\":1,"
                                        + "\"numberOfBuyedTickets\":10}]"));
    }
}
