package com.exlibris.domain.model.verificationToken;

import com.exlibris.domain.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @Author tomirszulc on 2019-07-06
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class VerificationToken {

    @Id
    @GeneratedValue
    private int id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "UserId")
    private User user;

    private LocalDate expiryDate;

    private LocalDate calculateExpiryDate() {
        return LocalDate.now();
    }
}
