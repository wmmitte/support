package com.sotec.support.Mappers.Ticket;

import com.sotec.support.Dtos.Ticket.MessageDto;
import com.sotec.support.Models.Ticket.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    //MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDto toDto(Message message);
    Message toEntity(MessageDto messageDto);

    List<MessageDto> toDtos(List<Message> messages);
    List<Message> toEntities(List<MessageDto> messageDtos);
}
