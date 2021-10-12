package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.dto.UserDTO;
import com.houseofwisdom.houseofwisdom.exceptions.NotFound500Exception;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public Long createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            logger.info("User created with id " + user.getId().toString());
        } catch (Exception ex) {
            logger.info("Failed to create user");
            ex.printStackTrace();
        }

        return user.getId();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = null;
        try {
            Optional<User> user = userService.getUser(id);
            userDTO = modelMapper.map(user.get(), UserDTO.class);
            logger.info("Successfully fetched user with user id: " + user.get().getId().toString());
        } catch (NotFound500Exception exception) {
            logger.info("Couldn't fetch user data.");
            exception.handleNoContent();
        }

        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllBooks() {
        List<UserDTO> userDTOS = new ArrayList<>();
        try {
            List<User> users = userService.getAllUsers();
            for(User user : users) {
                userDTOS.add(modelMapper.map(user, UserDTO.class));
            }
            logger.info("List of users fetched Successfully");
        } catch (Exception ex) {
            logger.info("Couldn't fetch user list data");
            ex.printStackTrace();
        }
        return userDTOS;
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user,
                           @PathVariable Long id) {
        User updatedUser = new User();
        try {
            updatedUser = userService.updateUser(user, id);
            logger.info("Sucseessfully updated user with user id: " + id.toString());
        } catch (Exception ex) {
            logger.info("Couldn't update user with user id: " + id.toString());
            ex.printStackTrace();
        }
        return updatedUser;
    }

    @DeleteMapping("/delete/{id}")
    void deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            logger.info("Deleted user with user id: " + id.toString());
        } catch (Exception ex) {
            logger.info("Couldn't delete user with user id: " + id.toString());
        }
    }
}
