package com.EventHorizon.EventHorizon.Controllers.TicketControllers;

import com.EventHorizon.EventHorizon.DTOs.TicketDto.BuyingAndRefundingDto;
import com.EventHorizon.EventHorizon.DTOs.TicketViewDto;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketTransactionsService;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketViewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket/")
@CrossOrigin("*")
public class TicketOperationController {
    @Autowired
    private TicketTransactionsService ticketTransactionsService;
    @Autowired
    private TicketViewService ticketViewService;

    @PutMapping("buyTicket/{clientInformationId}") //client
    public ResponseEntity buyTicket
            (HttpServletRequest request, @PathVariable int clientInformationId, @RequestBody List<BuyingAndRefundingDto> list) {

        ticketTransactionsService.buyTicketCollections(clientInformationId, list);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @PutMapping("refundTicket/{clientId}") //client
//    public ResponseEntity refundTicket
//            (HttpServletRequest request, @PathVariable int clientInformationId, @RequestBody BuyingAndRefundingDto buyingAndRefundingDto) {
//
//        int seatTypeId = buyingAndRefundingDto.getSeatTypeId();
//        int numOfTickets = buyingAndRefundingDto.getNumOfTickets();
//        ticketTransactionsService.refundTicket(clientInformationId, seatTypeId, numOfTickets);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @GetMapping("getAllTickets/{clientInformationId}/{eventId}") //all
    public ResponseEntity<List<TicketViewDto>> getAllTickets
            (@PathVariable int clientInformationId, @PathVariable int eventId) {

        List<TicketViewDto> list = ticketViewService.getTicketsDtoList(clientInformationId, eventId);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
