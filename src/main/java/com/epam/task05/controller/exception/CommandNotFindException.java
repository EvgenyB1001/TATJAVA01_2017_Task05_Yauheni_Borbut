package com.epam.task05.controller.exception;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class CommandNotFindException extends Exception {

    public CommandNotFindException() {
        super();
    }

    public CommandNotFindException(String message) {
        super(message);
    }

    public CommandNotFindException(String message, Exception e) {
        super(message, e);
    }

    public CommandNotFindException(Exception e) {
        super(e);
    }
}
