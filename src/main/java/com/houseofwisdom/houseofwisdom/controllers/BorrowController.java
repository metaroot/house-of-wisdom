package com.houseofwisdom.houseofwisdom.controllers;

import com.houseofwisdom.houseofwisdom.model.Borrow;
import com.houseofwisdom.houseofwisdom.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @PostMapping("/new")
    public Long borrowBook(@RequestBody Borrow borrow) {
        return borrowService.borrowBook(borrow);
    }
}
