package com.exlibris.service;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.repository.BookDao;
import com.exlibris.repository.RentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookDbService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RentalDbService rentalDbService;

    @Autowired
    private BookDbService bookDbService;

    @Autowired
    private FriendDbService friendDbService;

    @Autowired
    private RentalDao rentalDao;

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Book addBook(Book book) {
        return bookDao.save(book);
    }

    public Book deleteBook(int id) {
        Book bookForDelete = bookDao.findById(id);
        if (bookForDelete.isRented()) {
            int rentalId = bookForDelete.getRental().getId();
            rentalDbService.deleteRental(rentalId);
        }
        return bookDao.deleteById(id);
    }

    public Book getBookById(int id) {
        return bookDao.findById(id);
    }

    public Book updateBook(Book book) {
        return bookDao.save(book);
    }

}
