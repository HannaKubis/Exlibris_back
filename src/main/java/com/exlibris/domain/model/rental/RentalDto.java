package com.exlibris.domain.model.rental;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.friend.Friend;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentalDto {
    private int id;
    private Friend friend;
    private Book book;
    private LocalDate lendDate;
    private LocalDate returnDate;
}
