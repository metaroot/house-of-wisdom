package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public Long createBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return book.getId();
    }

    @GetMapping("/get/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@RequestBody Book book,
                           @PathVariable Long id) {
        return bookService.updateBook(book, id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
