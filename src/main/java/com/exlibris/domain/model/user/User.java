package com.exlibris.domain.model.user;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "UserId", unique = true)
    private long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    @JsonIgnore
    private String password;
    @JsonIgnore
    @Column(name = "book")
    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
    @JsonIgnore
    @Column(name = "friend")
    @OneToMany(
            targetEntity = Friend.class,
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<Friend> friends = new ArrayList<>();
    @JsonIgnore
    @Column(name = "rental")
    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    private List<Rental> rentals = new ArrayList<>();

    @Column(name = "activated")
    private boolean isActivated = false;
}
