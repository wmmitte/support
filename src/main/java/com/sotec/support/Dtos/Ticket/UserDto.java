package com.sotec.support.Dtos.Ticket;
import lombok.*;


import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private UUID id;
    private String login;
    private String password;
}
