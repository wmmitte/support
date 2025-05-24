package com.sotec.support.Services.Ticket;

import com.sotec.support.Dtos.Ticket.UserDto;

import java.util.List;
import java.util.UUID;


public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID id);
    void deleteUserById(UUID id);
}
