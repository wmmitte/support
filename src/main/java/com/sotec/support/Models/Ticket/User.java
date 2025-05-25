package com.sotec.support.Models.Ticket;

import com.sotec.support.Models.Enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "sup_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role; // USER | ADMIN
}
