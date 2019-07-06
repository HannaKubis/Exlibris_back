package com.exlibris.domain.model.book;

import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQuery(
        name = "Book.getBooks",
        query = "FROM Book WHERE user_id = :USER"
)

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOK")
public class Book {

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID", unique = true)
    private int id;

    @NotNull
    @Size(min = 2, max = 60)
    @Column(name = "BOOK_TITLE")
    private String title;

    @NotNull
    @Size(min = 2, max = 60)
    @Column(name = "BOOK_AUTHOR")
    private String author;

    @Min(value = 2)
    @Max(value = 10000)
    @Column(name = "BOOK_PAGES")
    private int pages;

    @NotNull
    @Column(name = "BOOK_IS_RENTED")
    private boolean isRented;

    @NotNull
    @Column(name = "BOOK_STATUS")
    private Read bookStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FRIEND_ID")
    private Friend friend;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "RENTAL_ID")
    private Rental rental;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    public Book(@NotNull String title,
                @NotNull String author,
                int pages,
                @NotNull boolean isRented,
                @NotNull Read bookStatus) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.isRented = isRented;
        this.bookStatus = bookStatus;
    }
}
