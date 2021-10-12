package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void saveUser(User user);
    Optional<User> getUser(Long id);
    User updateUser(User newUser, Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
