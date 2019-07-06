package com.exlibris.domain.model.rental;

import com.exlibris.domain.model.book.Book;
import com.exlibris.domain.model.friend.Friend;
import com.exlibris.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQuery(
        name = "Rental.getRentals",
        query = "FROM Rental WHERE user_id = :USER"
)

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RENTAL")
public class Rental {

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "RENTAL_ID", unique = true)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FRIEND_ID")
    private Friend friend;

    @NotNull
    @OneToOne(mappedBy = "rental")
    private Book book;

    @NotNull
    @Column(name = "RENTAL_LEND_DATE")
    private LocalDate lendDate;

    @Column(name = "RENTAL_RETURN_DATE")
    private LocalDate returnDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;

    public Rental(@NotNull Friend friend, @NotNull Book book, @NotNull LocalDate lendDate, LocalDate returnDate) {
        this.friend = friend;
        this.book = book;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }
}
