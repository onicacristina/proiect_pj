package com.company;

public class NoCompletedFieldException extends RuntimeException {
    public NoCompletedFieldException(String message)
    {
        super(message);
    }
}
