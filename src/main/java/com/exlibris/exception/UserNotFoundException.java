package com.exlibris.exception;

/**
 * @Author tomirszulc on 2019-07-05
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
