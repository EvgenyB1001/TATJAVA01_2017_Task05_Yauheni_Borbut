package com.epam.task05.service.exception;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class MenuServiceException extends Exception{

    public MenuServiceException() {
        super();
    }

    public MenuServiceException(String message) {
        super(message);
    }

    public MenuServiceException(String message, Exception e) {
        super(message, e);
    }

    public MenuServiceException(Exception e) {
        super(e);
    }
}
