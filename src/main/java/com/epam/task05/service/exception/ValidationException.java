package com.epam.task05.service.exception;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class ValidationException extends Exception {

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Exception e) {
        super(message, e);
    }

    public ValidationException(Exception e) {
        super(e);
    }
}
