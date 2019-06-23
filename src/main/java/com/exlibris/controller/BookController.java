package com.exlibris.controller;

import com.exlibris.domain.mapper.BookMapper;
import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.book.BookDto;
import com.exlibris.service.BookDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private static final String FETCHING_BOOKS = "Fetching books";
    private static final String ADDING_BOOK = "Adding book";
    private static final String UPDATING_BOOK = "Updating book";
    private static final String DELETING_BOOK = "Deleting book";

    @Autowired
    private BookDbService dbService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("books")
    public List<BookDto> getBooks() {
        LOGGER.info(FETCHING_BOOKS);
        return bookMapper.mapBookListToBookDtoList(dbService.getAllBooks());
    }

    @PostMapping("books")
    public BookDto addBook(@RequestBody Book book) {
        LOGGER.info(ADDING_BOOK);
        return bookMapper.mapBookToBookDto(dbService.addBook(book));
    }

    @PutMapping("books")
    public BookDto updateBook(@RequestBody Book book) {
        LOGGER.info(UPDATING_BOOK);
        return bookMapper.mapBookToBookDto(dbService.updateBook(book));
    }

    @DeleteMapping("books/{id}")
    public Book deleteBook(@PathVariable int id) {
        LOGGER.info(DELETING_BOOK);
        return dbService.deleteBook(id);
    }

}
