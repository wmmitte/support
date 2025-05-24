package com.sotec.support.Services.Ticket.Implementation;

import com.sotec.support.Dtos.Ticket.TicketDto;
import com.sotec.support.Mappers.Ticket.TicketMapper;
import com.sotec.support.Models.Enums.StatusEnum;
import com.sotec.support.Models.Ticket.Ticket;
import com.sotec.support.Models.Ticket.User;
import com.sotec.support.Repositories.Ticket.TicketRepository;
import com.sotec.support.Repositories.Ticket.UserRepository;
import com.sotec.support.Services.Ticket.TicketService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TicketDto createTicket(TicketDto objectDto) {
        //no matter for this object
        Ticket objectToSave = ticketMapper.toEntity(objectDto);
        objectToSave.setDateOuverture(Instant.now());
        //USER property management
        // creattorId <------ check with OUEDRAOGO
        objectToSave.setCreatorId(this.getUserToLinkToCurrentObject(objectDto).getId()); //<--- review this line with OUEDRAOGO
        Ticket savedObject = ticketRepository.save(objectToSave);
        return ticketMapper.toDto(savedObject);
    }

    @Override
    public TicketDto updateTicket(TicketDto objectDto) {
           //retrieve database object matching the dto id
            Ticket dbObjectToUpdate = ticketRepository
                .findById(objectDto.getId())
                .orElse(null);

            //if not null
            if (dbObjectToUpdate != null) {

                // object is not updatable
                if(!isUpdatable(dbObjectToUpdate)) return null;  // <----- to review With OUEDRAOGO
                // update Message
                if (
                    objectDto.getMessage() != null &&
                    !objectDto.getMessage().isBlank()
                ) {
                    dbObjectToUpdate.setMessage(
                        objectDto.getMessage().trim()
                    );
                }
                Ticket saveObject = ticketRepository.save(
                    dbObjectToUpdate
                );

                //others properties management
                // ...... <------ check with OUEDRAOGO

                return ticketMapper.toDto(saveObject);

            } else { //db object not found with the incoming id
                // Throw Exception here
                return objectDto;
            }

    }

    @Override
    public List<TicketDto> getAllTickets() {
        //get all Objects from database
        List<Ticket> dbObjects = ticketRepository.findAll();
        return ticketMapper.toDtos(dbObjects);
    }

    @Override
    public TicketDto getTicketById(UUID id) {
        Optional<Ticket> dbObject = ticketRepository.findById(id);
        return ticketMapper.toDto(dbObject.orElse(null));
    }

    @Override
    public void deleteTicketById(UUID id) {
        Ticket dbObjectToDelete = ticketRepository.findById(id).orElse(null);
        if (dbObjectToDelete != null) {
            //current object has no relations
            if (!(1 != 1)) {
                ticketRepository.delete(dbObjectToDelete);
            } else { //object has non-empty relations
                return;
            }
        }
    }

    @Override
    public TicketDto processTicket(UUID id) {
        Ticket dbObject = ticketRepository.findById(id).orElse(null);
        if (dbObject != null) {
            dbObject.setDateTraitement(Instant.now());
            dbObject.setStatut(StatusEnum.PENDING);
            Ticket savedObject = ticketRepository.save(dbObject);
            return ticketMapper.toDto(savedObject);
        }else{
            // Throw Error here
            return null;
        }
    }

    @Override
    public TicketDto closeTicket(UUID id) {
        Ticket dbObject = ticketRepository.findById(id).orElse(null);
        if (dbObject != null) {
            dbObject.setDateCloture(Instant.now());
            dbObject.setStatut(StatusEnum.CLOSED);
            //USER property management
            // closerID <------ check with OUEDRAOGO
            dbObject.setCloserId(this.getUserToLinkToCurrentObject(new TicketDto()).getId()); //<--- review this line with OUEDRAOGO
            Ticket savedObject = ticketRepository.save(dbObject);
            return ticketMapper.toDto(savedObject);
        }else{
            // Throw Error here
            return null;
        }
    }

    @Override
    public List<TicketDto> getNewlyCreatedTickets() {
        List<Ticket> dbObjects = ticketRepository.findTicketsByStatut(StatusEnum.NEW);
        return ticketMapper.toDtos(dbObjects);
    }

    @Override
    public List<TicketDto> getPendingTickets() {
        List<Ticket> dbObjects = ticketRepository.findTicketsByStatut(StatusEnum.PENDING);
        return ticketMapper.toDtos(dbObjects);
    }

    @Override
    public List<TicketDto> getClosedTickets() {
        List<Ticket> dbObjects = ticketRepository.findTicketsByStatut(StatusEnum.CLOSED);
        return ticketMapper.toDtos(dbObjects);
    }


    private User getUserToLinkToCurrentObject(TicketDto ticketDto) {
        List<User> users = userRepository.findAll();
        int randomUserIndex = ThreadLocalRandom.current().nextInt(0,users.size());
        return users.get(randomUserIndex);
    }

    private boolean isUpdatable(Ticket ticket) {
        return !(ticket.getStatut().equals(StatusEnum.CLOSED));
    }
}
