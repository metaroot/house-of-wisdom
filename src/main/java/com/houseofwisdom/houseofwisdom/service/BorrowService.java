package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.Borrow;
import com.houseofwisdom.houseofwisdom.model.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface BorrowService {
    Long borrowBook(Long userId, Long bookId);
    void returnBook(Long userId, Long bookId);
    Borrow updateBorrow(Borrow newBorrow, Long id);
    List<User> listOfUsersByBook(Long bookId);
    List<BookDTO> listOfBooksByUser(Long userId);
}
