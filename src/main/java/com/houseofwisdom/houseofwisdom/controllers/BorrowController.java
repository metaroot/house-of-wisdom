package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.Borrow;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @PostMapping("/take")
    public Long borrowBook(@RequestParam Long userId,
                           @RequestParam Long bookId) {
        return borrowService.borrowBook(userId, bookId);
    }

    @PostMapping("/return")
    public void returnBook(@RequestParam Long userId,
                           @RequestParam Long bookId) {
        borrowService.returnBook(userId, bookId);
    }

    @GetMapping("/list-of-users-by-book/{bookId}")
    public List<User> listOfUsersByBook(@PathVariable Long bookId) {
        return  borrowService.listOfUsersByBook(bookId);
    }

    @GetMapping("/list-of-books-by-user/{userId}")
    public List<BookDTO> listOfBooksByUser(@PathVariable Long userId) {
        return  borrowService.listOfBooksByUser(userId);
    }
}
