package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User newUser, Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(newUser.getUserName());
                    user.setBooksIssuedToTheUser(newUser.getBooksIssuedToTheUser());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
