package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.BookMeta;
import com.houseofwisdom.houseofwisdom.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)

public class BookControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
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
        mvc.perform(post("/books/create")
                        .content(String.valueOf(book)))
                .andExpect(status().isOk());
    }
}