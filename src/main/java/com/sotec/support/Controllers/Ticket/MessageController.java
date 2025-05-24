package com.sotec.support.Controllers.Ticket;

import com.sotec.support.Dtos.Ticket.MessageDto;
import com.sotec.support.Services.Ticket.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public MessageDto getMessageById(@PathVariable("id") final UUID id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public MessageDto createMessage(@RequestBody final MessageDto messageDto) {
        return messageService.createMessage(messageDto);
    }

    @PutMapping
    public MessageDto updateMessage(@RequestBody final MessageDto messageDto) {
        return messageService.updateMessage(messageDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable("id") final UUID id) {
        messageService.deleteMessageById(id);
    }
}
