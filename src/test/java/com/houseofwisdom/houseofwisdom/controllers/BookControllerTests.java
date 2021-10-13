package com.houseofwisdom.houseofwisdom.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.houseofwisdom.houseofwisdom.HouseofwisdomApplication;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.BookMeta;
import com.houseofwisdom.houseofwisdom.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties"
)
@ContextConfiguration(classes = HouseofwisdomApplication.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    @Test
    public void createTesting() throws Exception {


        Book book = new Book();
        BookMeta bookMeta = new BookMeta();
        Date date = null;

        bookMeta.setNumberOfPages(1000);
        bookMeta.setNumberOfAvailableCopies(25);
        bookMeta.setPublisherName("Bait-al-hikmah");

        bookMeta.setPublishedDate(date);
        book.setName("Akidah tu tahawi");
        book.setAuthorName("Imam Tahawi");
        book.setBookMeta(bookMeta);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(book);
        mvc.perform(post("/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}