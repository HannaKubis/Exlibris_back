package com.exlibris.domain.model.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author tomirszulc on 2019-07-03
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mail {
    private String mailTo;
    private String subject;
    private String message;

}
