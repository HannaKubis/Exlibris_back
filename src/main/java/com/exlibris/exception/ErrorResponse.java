package com.exlibris.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author tomirszulc on 2019-06-26
 */
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
