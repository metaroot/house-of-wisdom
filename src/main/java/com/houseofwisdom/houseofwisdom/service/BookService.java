package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BookService {
    void saveBook(Book book);
    Optional<Book> getBook(Long id);
    Book updateBook(Book newBook, Long id);
    void deleteBook(Long id);
}
