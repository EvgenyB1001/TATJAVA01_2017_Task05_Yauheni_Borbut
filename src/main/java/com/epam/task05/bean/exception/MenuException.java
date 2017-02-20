package com.epam.task05.bean.exception;

/**
 * Created by Yauheni_Borbut on 2/15/2017.
 */
public class MenuException extends Exception {

    public MenuException(String message) {
        super(message);
    }

    public MenuException(String message, Exception e) {
        super(message, e);
    }

    public MenuException(Exception e) {
        super(e);
    }

    public MenuException() {
        super();
    }
}
