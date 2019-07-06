package com.exlibris.exception;

/**
 * @Author tomirszulc on 2019-06-26
 */
public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String message) {
        super(message);
    }
}
