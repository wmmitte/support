package com.sotec.support.Services.Ticket;

import com.sotec.support.Dtos.Ticket.TicketDto;
import com.sotec.support.Models.Enums.StatusEnum;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDto);
    TicketDto updateTicket(TicketDto ticketDto);
    List<TicketDto> getAllTickets();
    TicketDto getTicketById(UUID id);
    void deleteTicketById(UUID id);
    TicketDto processTicket(UUID id);
    TicketDto closeTicket(UUID id);
    List<TicketDto> getNewlyCreatedTickets();
    List<TicketDto> getPendingTickets();
    List<TicketDto> getClosedTickets();

}
