package com.company;

public class NoBookSelectedException extends RuntimeException {
    public NoBookSelectedException(String message)
    {
        super(message);
    }
}
