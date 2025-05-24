package com.sotec.support.Dtos.Ticket;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDto {

    private UUID id;
    private String message;
    private UUID userId;
    private UUID ticketId;
    private String fichiers;
}
