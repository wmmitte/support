package com.sotec.support.Services.Ticket.Implementation;

import com.sotec.support.Dtos.Ticket.UserDto;
import com.sotec.support.Mappers.Ticket.UserMapper;
import com.sotec.support.Models.Ticket.User;
import com.sotec.support.Repositories.Ticket.UserRepository;
import com.sotec.support.Services.Ticket.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto objectDto) {
        if (!userRepository.existsByLogin(objectDto.getLogin())) {
            User objectToSave = userMapper.toEntity(objectDto);
            User savedObject = userRepository.save(objectToSave);
            return userMapper.toDto(savedObject);
        }

        return null;
    }

    @Override
    public UserDto updateUser(UserDto objectDto) {
        //check if the unique identifier is defined
        if (objectDto.getLogin() != null && !objectDto.getLogin().isBlank()) {
            //retrieve database object matching the dto id
            User dbObjectToUpdate = userRepository
                .findById(objectDto.getId())
                .orElse(null);

            //if not null, check if object unique identifier is present
            if (dbObjectToUpdate != null) {
                // db object and current object have different identifier
                if (
                    !objectDto
                        .getLogin()
                        .trim()
                        .equals(dbObjectToUpdate.getLogin())
                ) {
                    // no objects in the database with the same identifier
                    if (!userRepository.existsByLogin(objectDto.getLogin())) {
                        dbObjectToUpdate.setLogin(objectDto.getLogin().trim());
                        if (
                            objectDto.getPassword() != null &&
                            !objectDto.getPassword().isBlank()
                        ) {
                            dbObjectToUpdate.setPassword(
                                objectDto.getPassword().trim()
                            );
                        }
                        User saveObject = userRepository.save(dbObjectToUpdate);
                        return userMapper.toDto(saveObject);
                    } else { //some objects in database with the same identifier
                        //Throw Exception here
                        return objectDto;
                    }
                } else { //db and current objet have same unique identifier
                    //update only others properties of current object
                    if (
                        objectDto.getPassword() != null &&
                        !objectDto.getPassword().isBlank()
                    ) {
                        dbObjectToUpdate.setPassword(
                            objectDto.getPassword().trim()
                        );
                    }
                    User saveObject = userRepository.save(dbObjectToUpdate);
                    return userMapper.toDto(saveObject);
                }
            } else { //db object not found with the incoming id
                // Throw Exception here
                return objectDto;
            }
        } else { //object identifier was not provide
            //Throw exception here
            return objectDto;
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        //get all Objects from database
        List<User> dbObjects = userRepository.findAll();
        return userMapper.toDtos(dbObjects);
    }

    @Override
    public UserDto getUserById(UUID id) {
        Optional<User> dbObject = userRepository.findById(id);
        return userMapper.toDto(dbObject.orElse(null));
    }

    @Override
    public void deleteUserById(UUID id) {
        User dbObjectToDelete = userRepository.findById(id).orElse(null);
        if (dbObjectToDelete != null) {
            //current object has no relations
            if (!(1 != 1)) {
                userRepository.delete(dbObjectToDelete);
            } else { //object has non-empty relations
                return;
            }
        }
    }
}
