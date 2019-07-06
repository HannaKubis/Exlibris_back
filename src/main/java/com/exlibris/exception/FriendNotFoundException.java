package com.exlibris.exception;

/**
 * @Author tomirszulc on 2019-06-26
 */
public class FriendNotFoundException extends RuntimeException {
    public FriendNotFoundException(String message) {
        super(message);
    }
}
