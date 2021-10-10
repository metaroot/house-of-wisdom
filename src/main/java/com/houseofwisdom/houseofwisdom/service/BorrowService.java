package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.model.Borrow;

import java.util.List;
import java.util.Optional;

public interface BorrowService {
    Long borrowBook(Borrow borrow);
}
