package com.exlibris.service;

import com.exlibris.domain.model.book.Book;
import com.exlibris.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDbService {

    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Book addBook(Book book) {
        return bookDao.save(book);
    }

    public Book deleteBook(int id) {
        return bookDao.deleteById(id);
    }

    public Book getBookById(int id) {
        return bookDao.findById(id);
    }

    public Book updateBook(Book book) {
        return bookDao.save(book);
    }

}
