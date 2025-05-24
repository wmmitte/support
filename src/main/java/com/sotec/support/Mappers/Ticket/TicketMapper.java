package com.sotec.support.Mappers.Ticket;


import com.sotec.support.Dtos.Ticket.TicketDto;
import com.sotec.support.Models.Ticket.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
   // TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    TicketDto toDto(Ticket ticket);
    Ticket toEntity(TicketDto ticketDto);

    List<TicketDto> toDtos(List<Ticket> tickets);
    List<Ticket> toEntities(List<TicketDto> ticketDtos);
}
