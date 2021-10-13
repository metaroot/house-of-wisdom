package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)

public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService service;

    @Test
    public void createTesting() throws Exception {
        User user = new User();
        user.setUserName("Harun-Ur-Rashid");
        user.setBooksIssuedToTheUser(3);
        mvc.perform(post("/users/create")
                        .content(String.valueOf(user)))
                .andExpect(status().isOk());
    }
}
