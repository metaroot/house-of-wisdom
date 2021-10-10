package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.model.Borrow;
import com.houseofwisdom.houseofwisdom.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
