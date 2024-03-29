package com.exlibris.domain.model.friend;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.rental.Rental;
import com.exlibris.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQuery(
        name = "Friend.getFriends",
        query = "FROM Friend WHERE user_id = :USER"
)

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FRIEND")
public class Friend {

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "FRIEND_ID", unique = true)
    private int id;

    @NotNull
    @Size(min = 2, max = 60)
    @Column(name = "FRIEND_NAME")
    private String name;

    @OneToMany(targetEntity = Book.class,
            mappedBy = "friend",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Column(name = "FRIEND_BOOKS")
    private List<Book> books;

    @JsonIgnore
    @OneToMany(targetEntity = Rental.class,
            mappedBy = "friend",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Column(name = "RENTAL_ID")
    private List<Rental> rentals;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    public Friend(@NotNull String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}
