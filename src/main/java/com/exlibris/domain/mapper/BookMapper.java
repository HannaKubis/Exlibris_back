package com.exlibris.domain.mapper;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.book.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    public BookDto mapBookToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.isRented(),
                book.getBookStatus(),
                book.getFriend(),
                book.getRental());
    }

    public List<BookDto> mapBookListToBookDtoList(List<Book> bookList) {
        return bookList.stream()
                .map(book -> new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPages(),
                        book.isRented(),
                        book.getBookStatus(),
                        book.getFriend(),
                        book.getRental()))
                .collect(Collectors.toList());
    }

    public Book mapBookDtoToBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPages(),
                bookDto.isRented(),
                bookDto.getBookStatus(),
                bookDto.getFriend(),
                bookDto.getRental());
    }

    public List<Book> mapBookDtoListToBookList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(bookDto -> new Book(
                        bookDto.getId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getPages(),
                        bookDto.isRented(),
                        bookDto.getBookStatus(),
                        bookDto.getFriend(),
                        bookDto.getRental()))
                .collect(Collectors.toList());
    }
}
