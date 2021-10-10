package com.houseofwisdom.houseofwisdom.repository;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Optional<Book>> findByName(String name);
    List<Optional<Book>> findByAuthorName(String name);
}
