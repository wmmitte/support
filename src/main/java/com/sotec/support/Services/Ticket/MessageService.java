package com.sotec.support.Services.Ticket;

import com.sotec.support.Dtos.Ticket.MessageDto;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageDto createMessage(MessageDto messageDto);
    MessageDto updateMessage(MessageDto messageDto);
    List<MessageDto> getAllMessages();
    MessageDto getMessageById(UUID id);
    void deleteMessageById(UUID id);
}
