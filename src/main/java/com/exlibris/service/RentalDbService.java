package com.exlibris.service;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.user.User;
import com.exlibris.exception.RentalNotFoundException;
import com.exlibris.repository.RentalDao;
import com.exlibris.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalDbService {

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private BookDbService bookDbService;

    @Autowired
    private FriendDbService friendDbService;

    @Autowired
    private UserDao userDao;

    public List<Rental> getAllRentals() {
        return rentalDao.findAll();
    }

    public Rental addRental(Rental rental) {
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        rental.setUser(loggedUser);
        Book rentedBook = bookDbService.getBookById(rental.getBook().getId());
        Friend borrower = friendDbService.getFriendById(rental.getFriend().getId());
        List<Book> borrowerBooks = borrower.getBooks();
        List<Rental> friendRentals = borrower.getRentals();
        rentedBook.setRented(true);
        rentedBook.setFriend(borrower);
        rentedBook.setRental(rental);

        borrowerBooks.add(rentedBook);
        borrower.setBooks(borrowerBooks);
        if(friendRentals == null) {
            friendRentals = new ArrayList<>();
        }
        friendRentals.add(rental);
        borrower.setRentals(friendRentals);


        rentalDao.save(rental);
        bookDbService.updateBook(rentedBook);
        friendDbService.updateFriend(borrower);


        return rentalDao.save(rental);
    }

    public void deleteRental(int id) throws RentalNotFoundException {
        if(rentalDao.findById(id) == null) {
            throw new RentalNotFoundException("Cannot delete rental");
        }
        Rental rental = rentalDao.findById(id);
        Book rentedBook = bookDbService.getBookById(rental.getBook().getId());
        Friend borrower = friendDbService.getFriendById(rental.getFriend().getId());
        List<Book> borrowerBooks = borrower.getBooks();
        rentedBook.setRented(false);
        rentedBook.setFriend(null);
        rentedBook.setRental(null);
        borrowerBooks.remove(rentedBook);
        borrower.setBooks(borrowerBooks);

        bookDbService.updateBook(rentedBook);
        friendDbService.updateFriend(borrower);

        rentalDao.deleteById(id);
    }

    public Rental updateRental(Rental rental) {
        return rentalDao.save(rental);
    }

    public List<Rental> getUserRentalList() {
        User loggedUser = userDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return rentalDao.getRentals(loggedUser.getId());
    }
}
