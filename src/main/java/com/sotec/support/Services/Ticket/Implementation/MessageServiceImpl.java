package com.sotec.support.Services.Ticket.Implementation;

import com.sotec.support.Dtos.Ticket.MessageDto;
import com.sotec.support.Mappers.Ticket.MessageMapper;
import com.sotec.support.Models.Ticket.Message;
import com.sotec.support.Repositories.Ticket.MessageRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sotec.support.Services.Ticket.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MessageDto createMessage(MessageDto objectDto) {
        //no matter for this object
        Message objectToSave = messageMapper.toEntity(objectDto);
        //USER and TICKET properties management
        // userId <------ check with OUEDRAOGO
        // ticketId <------ check with OUEDRAOGO
        Message savedObject = messageRepository.save(objectToSave);
        return messageMapper.toDto(savedObject);
    }

    @Override
    public MessageDto updateMessage(MessageDto objectDto) {
        //retrieve database object matching the dto id
        Message dbObjectToUpdate = messageRepository
            .findById(objectDto.getId())
            .orElse(null);

        //if not null
        if (dbObjectToUpdate != null) {
            // update Message
            if (
                objectDto.getMessage() != null &&
                !objectDto.getMessage().isBlank()
            ) {
                dbObjectToUpdate.setMessage(objectDto.getMessage().trim());
            }
            Message saveObject = messageRepository.save(dbObjectToUpdate);

            //others properties management (User and Ticket)
            // ...... <------ check with OUEDRAOGO

            return messageMapper.toDto(saveObject);
        } else { //db object not found with the incoming id
            // Throw Exception here
            return objectDto;
        }
    }

    @Override
    public List<MessageDto> getAllMessages() {
        //get all Objects from database
        List<Message> dbObjects = messageRepository.findAll();
        return messageMapper.toDtos(dbObjects);
    }

    @Override
    public MessageDto getMessageById(UUID id) {
        Optional<Message> dbObject = messageRepository.findById(id);
        return messageMapper.toDto(dbObject.orElse(null));
    }

    @Override
    public void deleteMessageById(UUID id) {
        Message dbObjectToDelete = messageRepository.findById(id).orElse(null);
        if (dbObjectToDelete != null) {
            //current object has no relations
            if (!(1 != 1)) {
                messageRepository.delete(dbObjectToDelete);
            } else { //object has non-empty relations
                return;
            }
        }
    }

}
