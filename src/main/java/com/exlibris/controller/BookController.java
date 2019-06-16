package com.exlibris.controller;

import com.exlibris.domain.mapper.BookMapper;
import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.book.BookDto;
import com.exlibris.service.BookDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class BookController {

    @Autowired
    private BookDbService dbService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("books")
    public List<BookDto> getBooks() {
        return bookMapper.mapBookListToBookDtoList(dbService.getAllBooks());
    }

    @PostMapping("books")
    public BookDto addBook(@RequestBody Book book) {
        return bookMapper.mapBookToBookDto(dbService.addBook(book));
    }

    @PutMapping("books/")
    public BookDto updateBook(@RequestBody Book book) {
        return bookMapper.mapBookToBookDto(dbService.updateBook(book));
    }

    @DeleteMapping("books/{id}")
    public Book deleteBook(@PathVariable int id) {
        return dbService.deleteBook(id);
    }

}
