package com.sotec.support.Models.Ticket;

import com.sotec.support.Models.Enums.CategoriesEnum;
import com.sotec.support.Models.Enums.PrioritesEnum;
import com.sotec.support.Models.Enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "sup_tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private CategoriesEnum categorie;

    @Enumerated(EnumType.STRING)
    private PrioritesEnum priorite;

    @Enumerated(EnumType.STRING)
    private StatusEnum statut;

    @Column(columnDefinition = "TEXT")
    private String message;

    private Instant dateOuverture;
    private Instant dateTraitement;
    private Instant dateCloture;

    private UUID creatorId;
    private UUID closerId;

}
