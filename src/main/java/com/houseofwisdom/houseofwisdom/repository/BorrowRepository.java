package com.houseofwisdom.houseofwisdom.repository;

import com.houseofwisdom.houseofwisdom.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
