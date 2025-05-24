package com.sotec.support.Repositories.Ticket;

import com.sotec.support.Models.Ticket.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByLogin(String login);

    User findByLogin(String login);
}
