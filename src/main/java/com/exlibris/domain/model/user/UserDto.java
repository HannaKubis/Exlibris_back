package com.exlibris.domain.model.user;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tomirszulc on 2019-06-29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String password;
    private List<Book> books = new ArrayList<>();
    private List<Friend> friends = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();
    private boolean isActivated;
}
