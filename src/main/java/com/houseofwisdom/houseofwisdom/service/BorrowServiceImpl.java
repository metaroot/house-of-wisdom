package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.Borrow;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.repository.BookRepository;
import com.houseofwisdom.houseofwisdom.repository.BorrowRepository;
import com.houseofwisdom.houseofwisdom.repository.UserRepository;
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
    public Long borrowBook(Borrow borrow) {
        Long borrowId = null;
        try {
            Long bookId = borrow.getBook_id();
            Long userId = borrow.getUser_id();

            if(bookId != null && userId != null) {
                Optional<User> user = userService.getUser(userId);
                Integer booksIssuedToTheUser = user.get().getBooksIssuedToTheUser();

                Optional<Book> book = bookService.getBook(bookId);
                Integer numberOfAvailableCopies = book.get().getBookMeta().getNumberOfAvailableCopies();

                if(booksIssuedToTheUser
                        + HouseOfWisdomConstants.MAX_NUMBER_OF_ISSUED_BOOKS_PER_BORROW
                        <= HouseOfWisdomConstants.MAX_NUMBER_OF_ISSUED_BOOKS_TO_AN_USER
                        && numberOfAvailableCopies > 0) {

                    borrowRepository.save(borrow);

                    book.get().getBookMeta().setNumberOfAvailableCopies(numberOfAvailableCopies - 1);
                    bookService.updateBook(book.get(), bookId);

                    user.get().setBooksIssuedToTheUser(booksIssuedToTheUser + 1);
                    userService.updateUser(user.get(), userId);

                    borrowId = borrow.getId();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return borrowId;
    }
}
