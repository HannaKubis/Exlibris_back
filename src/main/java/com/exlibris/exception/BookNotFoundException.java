package com.exlibris.exception;

/**
 * @Author tomirszulc on 2019-06-26
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
