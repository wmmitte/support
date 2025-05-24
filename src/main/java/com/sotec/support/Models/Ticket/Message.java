package com.sotec.support.Models.Ticket;

import jakarta.persistence.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "sup_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String message;
    private UUID userId;
    private UUID ticketId;
    private String fichiers;

}
