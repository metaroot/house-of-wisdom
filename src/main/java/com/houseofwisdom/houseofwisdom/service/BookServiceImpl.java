package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    //Create
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    //Read
    public Optional<Book> getBook(Long id) {
        return  bookRepository.findById(id);
    }

    //Update
    public Book updateBook(Book newBook, Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setName(newBook.getName());
                    book.setAuthorName(newBook.getAuthorName());
                    book.setBookMeta(newBook.getBookMeta());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepository.save(newBook);
                });
    }

    //Delete
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    //Find by book name
    public List<Optional<Book>> findByName(String name) {
        return bookRepository.findByName(name);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Find by author name
    public List<Optional<Book>> findByAuthorName(String name) {
        return bookRepository.findByAuthorName(name);
    }
}
