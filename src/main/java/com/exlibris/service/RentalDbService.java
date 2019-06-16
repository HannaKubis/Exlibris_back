package com.exlibris.service;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.repository.RentalDao;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Rental> getAllRentals() {
        return rentalDao.findAll();
    }

    public Rental addRental(Rental rental) {
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

    public Rental deleteRental(int id) {
        Rental rental = getRental(id);
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

        return rentalDao.deleteById(id);
    }

    public Rental getRental(int id) {
        return rentalDao.findById(id);
    }

    public Rental updateRental(Rental rental) {
        return rentalDao.save(rental);
    }

}
