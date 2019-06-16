package com.exlibris.domain.model.friend;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FriendDto {
    private int id;
    private String name;
    private List<Book> books;
    private List<Rental> rental;
}
