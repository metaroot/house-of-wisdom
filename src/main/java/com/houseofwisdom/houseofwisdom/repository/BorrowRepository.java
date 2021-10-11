package com.houseofwisdom.houseofwisdom.repository;

import com.houseofwisdom.houseofwisdom.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Borrow findByUserIdAndBookId(Long userId, Long bookId);
    List<Borrow> findByBookId(Long bookId);
    List<Borrow> findByUserId(Long userId);
}
