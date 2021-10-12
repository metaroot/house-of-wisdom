package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.dto.UserDTO;
import com.houseofwisdom.houseofwisdom.exceptions.NotFound500Exception;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public Long createUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
        } catch (Exception ex) {
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
        } catch (NotFound500Exception exception) {
            exception.handleNoContent();
        }

        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user,
                           @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
