package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.dto.UserDTO;
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
        userService.saveUser(user);
        return user.getId();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);
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
