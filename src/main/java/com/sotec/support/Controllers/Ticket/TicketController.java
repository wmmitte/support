package com.sotec.support.Controllers.Ticket;

import com.sotec.support.Dtos.Ticket.TicketDto;
import com.sotec.support.Services.Ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable("id") final UUID id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping
    public TicketDto createTicket(@RequestBody final TicketDto ticketDto) {
        return ticketService.createTicket(ticketDto);
    }

    @PutMapping
    public TicketDto updateTicket(@RequestBody final TicketDto ticketDto) {
        return ticketService.updateTicket(ticketDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable("id") final UUID id){
        ticketService.deleteTicketById(id);
    }

    @GetMapping("/{id}/process")
    public TicketDto beginTicketProcessing(@PathVariable("id") final UUID id) {
        return ticketService.processTicket(id);
    }

    @GetMapping("/{id}/close")
    public TicketDto closeTicket(@PathVariable("id") final UUID id) {
       return ticketService.closeTicket(id);
    }

    /*
    * Specifics resources queries
    * */
    @GetMapping("/created")
    public List<TicketDto> getNewlyCreatedTickets() {
        return ticketService.getNewlyCreatedTickets();
    }

    @GetMapping("/closed")
    public List<TicketDto> getClosedTickets() {
        return ticketService.getClosedTickets();
    }

    @GetMapping("/pending")
    public List<TicketDto> getPendingTickets() {
        return ticketService.getPendingTickets();
    }

}
