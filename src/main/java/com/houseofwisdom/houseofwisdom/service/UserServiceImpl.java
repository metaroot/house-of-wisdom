package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    //Create
    public void saveUser(User user) {
        userRepository.save(user);
    }

    //Read
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    //Update
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Delete
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
