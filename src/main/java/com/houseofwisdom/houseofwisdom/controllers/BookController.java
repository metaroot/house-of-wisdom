package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.HouseofwisdomApplication;
import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.service.BookService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public Long createBook(@RequestBody Book book) {
        bookService.saveBook(book);
        if(book.getId() != null) {
            logger.info("New book Created with book id: " + book.getId());
        }
        return book.getId();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.getBook(id);
        BookDTO bookDTO = modelMapper.map(book.get(), BookDTO.class);
        return ResponseEntity.ok().body(bookDTO);
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

    @GetMapping("/search-by-book-name/{name}")
    public List<BookDTO>findByBookName(@PathVariable String name) {
        List<Optional<Book>> books = bookService.findByName(name);
        List<BookDTO> bookDTOs = new ArrayList<>();

        for(Optional<Book> book : books) {
            bookDTOs.add(modelMapper.map(book.get(), BookDTO.class));
        }

        return bookDTOs;
    }

    @GetMapping("/search-by-author-name/{name}")
    public List<BookDTO>findByAuthorName(@PathVariable String name) {
        List<Optional<Book>> books = bookService.findByAuthorName(name);
        List<BookDTO> bookDTOs = new ArrayList<>();

        for(Optional<Book> book : books) {
            bookDTOs.add(modelMapper.map(book.get(), BookDTO.class));
        }
        return bookDTOs;
    }
}
