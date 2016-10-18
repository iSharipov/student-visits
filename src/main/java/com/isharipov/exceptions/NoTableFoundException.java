package com.isharipov.exceptions;

/**
 * Created by Илья on 18.10.2016.
 */
public class NoTableFoundException extends RuntimeException {
    public NoTableFoundException(String message) {
        super(message);
    }
}
