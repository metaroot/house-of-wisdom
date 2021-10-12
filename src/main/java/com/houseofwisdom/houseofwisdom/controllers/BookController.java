package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.HouseofwisdomApplication;
import com.houseofwisdom.houseofwisdom.dto.BookDTO;

import com.houseofwisdom.houseofwisdom.exceptions.BadRequest400Exception;
import com.houseofwisdom.houseofwisdom.exceptions.NotFound500Exception;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.service.BookService;
import javassist.NotFoundException;
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
        try {
            bookService.saveBook(book);
            if(book.getId() == null) {
                throw new BadRequest400Exception();
            } else {
                logger.info("New book created successfully with book id: " + book.getId().toString());
            }

        } catch (BadRequest400Exception exception) {
            logger.info("Book creation Failed.");
            exception.handleBadRequest();
        }
        return book.getId();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        BookDTO bookDTO = null;
        try {
            Optional<Book> book = bookService.getBook(id);
            bookDTO = modelMapper.map(book.get(), BookDTO.class);
            if(book.get().getId() != null) {
                logger.info("Book Info fetched successfully");
            }
        } catch (NotFound500Exception exception) {
            logger.info("Couldn't fetch book info");
            exception.handleNoContent();
        }

        return ResponseEntity.ok().body(bookDTO);
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        try {
            List<Book> books = bookService.getAllBooks();
            for(Book book : books) {
                bookDTOS.add(modelMapper.map(book, BookDTO.class));
            }
            logger.info("List of books fetched Successfully");
        } catch (Exception ex) {
            logger.info("Couldn't fetch book list data");
            ex.printStackTrace();
        }
        return bookDTOS;
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@RequestBody Book book,
                           @PathVariable Long id) {
        Book updatedBook = new Book();
        try {
            updatedBook = bookService.updateBook(book, id);
            logger.info("Updated book info with book id: " + id.toString());
        } catch (Exception ex) {
            logger.info("Couldn't update book info with book id: " + id.toString());
        }
        return updatedBook;
    }

    @DeleteMapping("/delete/{id}")
    void deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            logger.info("Book deleted with id: " + id.toString());
        } catch (Exception ex) {
            logger.info("Couldn't delete the book with id: " + id.toString());
        }
    }

    @GetMapping("/search-by-book-name/{name}")
    public List<BookDTO>findByBookName(@PathVariable String name) {
        List<BookDTO> bookDTOs = new ArrayList<>();
        try {
            List<Optional<Book>> books = bookService.findByName(name);

            for(Optional<Book> book : books) {
                bookDTOs.add(modelMapper.map(book.get(), BookDTO.class));
            }
            logger.info("Searching by book name done successfully.");
        } catch (Exception ex) {
            logger.info("Searching by author name failed");
            ex.printStackTrace();
        }

        return bookDTOs;
    }

    @GetMapping("/search-by-author-name/{name}")
    public List<BookDTO>findByAuthorName(@PathVariable String name) {
        List<BookDTO> bookDTOs = new ArrayList<>();
        try {
            List<Optional<Book>> books = bookService.findByAuthorName(name);

            for(Optional<Book> book : books) {
                bookDTOs.add(modelMapper.map(book.get(), BookDTO.class));
            }
            logger.info("Seaching by author name done successfully.");
        } catch (Exception ex) {
            logger.info("Searching by autho name failed.");
            ex.printStackTrace();
        }

        return bookDTOs;
    }
}
