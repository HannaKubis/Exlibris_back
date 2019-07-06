package com.exlibris.service;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.user.User;
import com.exlibris.exception.BookNotFoundException;
import com.exlibris.repository.BookDao;
import com.exlibris.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDbService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RentalDbService rentalDbService;

    @Autowired
    private UserDao userDao;

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Book addBook(Book book) {
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        book.setUser(loggedUser);
        return bookDao.save(book);
    }

    public void deleteBook(int id) throws BookNotFoundException {
        Book bookForDelete = bookDao.findById(id);
        if(bookForDelete == null) {
            throw new BookNotFoundException("Cannot delete book");
        }
        if (bookForDelete.isRented()) {
            int rentalId = bookForDelete.getRental().getId();
            rentalDbService.deleteRental(rentalId);
        }
        bookDao.deleteById(id);
    }

    public Book getBookById(int id) throws BookNotFoundException {
        if(bookDao.findById(id) == null) {
            throw new BookNotFoundException("Cannot find book");
        }
        return bookDao.findById(id);
    }

    public Book updateBook(Book book) {
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        book.setUser(loggedUser);
        return bookDao.save(book);
    }

    public List<Book> getUserBookList() {
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return bookDao.getBooks(loggedUser.getId());
    }

}
