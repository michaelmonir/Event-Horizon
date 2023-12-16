package com.EventHorizon.EventHorizon.Controllers.TicketControllers;

import com.EventHorizon.EventHorizon.DTOs.TicketDto.BuyingAndRefundingDto;
import com.EventHorizon.EventHorizon.Services.Tickets.TicketTransactionsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket/")
@CrossOrigin("*")
public class TicketOperationController {
    @Autowired
    private TicketTransactionsService ticketTransactionsService;

    @PutMapping("buyTicket/{clientId}") //client
    public ResponseEntity buyTicket
            (HttpServletRequest request, @PathVariable int clientInformationId, @RequestBody BuyingAndRefundingDto buyingAndRefundingDto) {

        int seatTypeId = buyingAndRefundingDto.getSeatTypeId();
        int numOfTickets = buyingAndRefundingDto.getNumOfTickets();
        ticketTransactionsService.buyTicket(clientInformationId, seatTypeId, numOfTickets);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("refundTicket/{clientId}") //client
    public ResponseEntity refundTicket
            (HttpServletRequest request, @PathVariable int clientInformationId, @RequestBody BuyingAndRefundingDto buyingAndRefundingDto) {

        int seatTypeId = buyingAndRefundingDto.getSeatTypeId();
        int numOfTickets = buyingAndRefundingDto.getNumOfTickets();
        ticketTransactionsService.refundTicket(clientInformationId, seatTypeId, numOfTickets);
        return new ResponseEntity(HttpStatus.OK);
    }
}
