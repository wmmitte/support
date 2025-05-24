package com.sotec.support.Repositories.Ticket;

import com.sotec.support.Models.Enums.StatusEnum;
import com.sotec.support.Models.Ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findTicketsByStatut(StatusEnum statut);

}
