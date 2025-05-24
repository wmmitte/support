package com.sotec.support.Dtos.Ticket;

import com.sotec.support.Models.Enums.CategoriesEnum;
import com.sotec.support.Models.Enums.PrioritesEnum;
import com.sotec.support.Models.Enums.StatusEnum;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDto {

    private UUID id;
    private CategoriesEnum categorie;
    private PrioritesEnum priorite;
    private StatusEnum statut;
    private String message;
    private Instant dateOuverture;
    private Instant dateTraitement;
    private Instant dateCloture;
    private UUID creatorId;
    private UUID closerId;
    private List<String> fichiers;
}
