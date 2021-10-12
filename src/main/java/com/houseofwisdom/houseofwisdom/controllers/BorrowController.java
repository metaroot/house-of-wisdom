package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.service.BorrowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/borrow")
public class BorrowController {
    private static final Logger logger = LoggerFactory.getLogger(BorrowController.class);
    @Autowired
    BorrowService borrowService;

    @PostMapping("/take")
    public Long borrowBook(@RequestParam Long userId,
                           @RequestParam Long bookId) {
        Long borrowId = null;
        try {
            borrowId = borrowService.borrowBook(userId, bookId);
            logger.info("User id: " + userId.toString() + "borrowed book id: " + bookId.toString());
        } catch (Exception ex) {
            logger.info("User id: " + userId.toString() + "couldn't borrow book id: " + bookId.toString());
            ex.printStackTrace();
        }
        return borrowId;
    }

    @PostMapping("/return")
    public void returnBook(@RequestParam Long userId,
                           @RequestParam Long bookId) {
        try {
            borrowService.returnBook(userId, bookId);
            logger.info("User id: " + userId.toString() + "returned book id: " + bookId.toString());
        } catch (Exception ex) {
            logger.info("User id: " + userId.toString() + "couldn't return book id: " + bookId.toString());
            ex.printStackTrace();
        }

    }

    @GetMapping("/list-of-users-by-book/{bookId}")
    public List<User> listOfUsersByBook(@PathVariable Long bookId) {
        List<User> users = new ArrayList<>();
        try {
            users = borrowService.listOfUsersByBook(bookId);
            logger.info("Successfully fetched list of users with book id: " + bookId.toString());
        } catch (Exception ex) {
            logger.info("Couldn't fetch list of users with book id: " + bookId.toString());
            ex.printStackTrace();
        }
        return  users;
    }

    @GetMapping("/list-of-books-by-user/{userId}")
    public List<BookDTO> listOfBooksByUser(@PathVariable Long userId) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        try {
            bookDTOS = borrowService.listOfBooksByUser(userId);
            logger.info("Successfully fetched list of books with user id: " + userId.toString());
        } catch (Exception ex) {
            logger.info("Couldn't fetch list of books with user id: " + userId.toString());
            ex.printStackTrace();
        }
        return  bookDTOS;
    }
}
