package com.houseofwisdom.houseofwisdom.service;

import com.houseofwisdom.houseofwisdom.dto.BookDTO;
import com.houseofwisdom.houseofwisdom.model.Book;
import com.houseofwisdom.houseofwisdom.model.Borrow;
import com.houseofwisdom.houseofwisdom.model.User;
import com.houseofwisdom.houseofwisdom.repository.BookRepository;
import com.houseofwisdom.houseofwisdom.repository.BorrowRepository;
import com.houseofwisdom.houseofwisdom.utils.HouseOfWisdomConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowServiceImpl implements BorrowService{
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }

    public Long borrowBook(Long userId, Long bookId) {
        Long borrowId = null;
        Optional<User> user = userService.getUser(userId);
        Optional<Book> book = bookService.getBook(bookId);


        try {
            if(book.isPresent() && user.isPresent()) {
                Integer booksIssuedToTheUser = user.get().getBooksIssuedToTheUser();
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
                        borrowId = updateBorrow(borrow, borrow.getId()).getId();
                    } else {
                        Borrow newBorrow = new Borrow();
                        newBorrow.setUserId(userId);
                        newBorrow.setBookId(bookId);
                        newBorrow.setCombinationOccurences(1);
                        borrowId = borrowRepository.save(newBorrow).getId();
                    }
                    book.get().getBookMeta().setNumberOfAvailableCopies(numberOfAvailableCopies - 1);
                    bookService.updateBook(book.get(), bookId);

                    user.get().setBooksIssuedToTheUser(booksIssuedToTheUser + 1);
                    userService.updateUser(user.get(), userId);

                } else {
                    borrowId = null;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return borrowId;
    }

    public void returnBook(Long userId, Long bookId) {
        Long borrowId = null;
        Optional<User> user = userService.getUser(userId);
        Optional<Book> book = bookService.getBook(bookId);

        try {
            if(book.isPresent() && user.isPresent()) {
                Integer booksIssuedToTheUser = user.get().getBooksIssuedToTheUser();
                Integer numberOfAvailableCopies = book.get().getBookMeta().getNumberOfAvailableCopies();

                Borrow borrow = null;
                try {
                    borrow = borrowRepository.findByUserIdAndBookId(userId, bookId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if(borrow != null) {
                    Integer numOfCombinationOccurences = borrow.getCombinationOccurences();
                    if(numOfCombinationOccurences > 1) {
                        //reduce num Of Combination Occurences and update
                        borrow.setCombinationOccurences(numOfCombinationOccurences - 1);
                        updateBorrow(borrow, borrow.getId());
                    } else {
                        deleteBorrow(borrowId);
                    }
                }
                book.get().getBookMeta().setNumberOfAvailableCopies(numberOfAvailableCopies + 1);
                bookService.updateBook(book.get(), bookId);

                user.get().setBooksIssuedToTheUser(booksIssuedToTheUser - 1);
                userService.updateUser(user.get(), userId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<User> listOfUsersByBook(Long bookId) {
        List<User> usersTakenABook = new ArrayList<>();
        List<Borrow> allBorrowsOfTheBook = new ArrayList<>();

        try {
            allBorrowsOfTheBook = borrowRepository.findByBookId(bookId);
            if(allBorrowsOfTheBook != null) {
                for(Borrow borrow : allBorrowsOfTheBook) {
                    Optional<User> user = userService.getUser(borrow.getUserId());
                    usersTakenABook.add(user.get());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return usersTakenABook;
    }

    public List<BookDTO> listOfBooksByUser(Long userId) {
        List<BookDTO> booksTakenByAUser = new ArrayList<>();
        List<Borrow> allBorrowsOfTheBook = new ArrayList<>();

        try {
            allBorrowsOfTheBook = borrowRepository.findByUserId(userId);
            if(allBorrowsOfTheBook != null) {
                for(Borrow borrow : allBorrowsOfTheBook) {
                    Optional<Book> book = bookService.getBook(borrow.getBookId());
                    BookDTO bookDTO = modelMapper.map(book.get(), BookDTO.class);
                    booksTakenByAUser.add(bookDTO);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return booksTakenByAUser;
    }
}
