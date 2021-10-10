package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.Borrow;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.repository.BorrowRepository;
import com.houseofwisdom.houseofwisdom.utils.HouseOfWisdomConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService{
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    public Borrow updateBorrow(Borrow newBorrow, Long id) {
        return borrowRepository.findById(id)
                .map(borrow -> {
                    borrow.setBookId(newBorrow.getBookId());
                    borrow.setUserId(newBorrow.getUserId());
                    borrow.setCombinationOccurences(newBorrow.getCombinationOccurences());
                    return borrowRepository.save(borrow);
                })
                .orElseGet(() -> {
                    newBorrow.setId(id);
                    return borrowRepository.save(newBorrow);
                });
    }
    public Long borrowBook(Long userId, Long bookId) {
        Long borrowId = null;
        try {
            if(bookId != null && userId != null) {
                Optional<User> user = userService.getUser(userId);
                Integer booksIssuedToTheUser = user.get().getBooksIssuedToTheUser();

                Optional<Book> book = bookService.getBook(bookId);
                Integer numberOfAvailableCopies = book.get().getBookMeta().getNumberOfAvailableCopies();

                Borrow borrow = null;
                try {
                    borrow = borrowRepository.findByUserIdAndBookId(userId, bookId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if(booksIssuedToTheUser
                        + HouseOfWisdomConstants.MAX_NUMBER_OF_ISSUED_BOOKS_PER_BORROW
                        <= HouseOfWisdomConstants.MAX_NUMBER_OF_ISSUED_BOOKS_TO_AN_USER
                        && numberOfAvailableCopies > 0) {
                    if(borrow != null) {
                        borrow.setCombinationOccurences(borrow.getCombinationOccurences() + 1);
                        updateBorrow(borrow, borrow.getId());
                    } else {
                        Borrow newBorrow = new Borrow();
                        newBorrow.setUserId(userId);
                        newBorrow.setBookId(bookId);
                        newBorrow.setCombinationOccurences(1);
                        borrowRepository.save(newBorrow);
                    }
                    book.get().getBookMeta().setNumberOfAvailableCopies(numberOfAvailableCopies - 1);
                    bookService.updateBook(book.get(), bookId);

                    user.get().setBooksIssuedToTheUser(booksIssuedToTheUser + 1);
                    userService.updateUser(user.get(), userId);

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return borrowRepository.findByUserIdAndBookId(userId, bookId).getId();
    }
}
