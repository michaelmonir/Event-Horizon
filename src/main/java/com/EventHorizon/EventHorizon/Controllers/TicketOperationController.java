package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.TicketDto.BuyingAndRefundingDto;
import com.EventHorizon.EventHorizon.Services.TicketService;
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
    private TicketService ticketService;

    @PutMapping("buyTicket/{clientId}") //organizer,admin
    public ResponseEntity buyTicket
            (HttpServletRequest request, @PathVariable int clientInformationId, @RequestBody BuyingAndRefundingDto buyingAndRefundingDto) {

        int seatTypeId = buyingAndRefundingDto.getSeatTypeId();
        int numOfTickets = buyingAndRefundingDto.getNumOfTickets();
        ticketService.buyTicket(clientInformationId, seatTypeId, numOfTickets);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("refundTicket/{clientId}") //organizer,admin
    public ResponseEntity refundTicket
            (HttpServletRequest request, @PathVariable int clientInformationId, @RequestBody BuyingAndRefundingDto buyingAndRefundingDto) {

        int seatTypeId = buyingAndRefundingDto.getSeatTypeId();
        int numOfTickets = buyingAndRefundingDto.getNumOfTickets();
        ticketService.refundTicket(clientInformationId, seatTypeId, numOfTickets);
        return new ResponseEntity(HttpStatus.OK);
    }
}
