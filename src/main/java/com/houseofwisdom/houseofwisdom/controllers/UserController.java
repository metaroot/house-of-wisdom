package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public Long createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user.getId();
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user,
                           @PathVariable Long id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteEmployee(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
