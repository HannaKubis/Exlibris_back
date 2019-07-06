package com.exlibris.domain.model.book;

import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private String author;
    private int pages;
    private boolean isRented;
    private Read bookStatus;
    private Friend friend;
    private Rental rental;
    private User user;
}
