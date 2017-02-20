package com.epam.task05.dao.parser.dom.exception;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class DOMException extends Exception {

    public DOMException(String message) {
        super(message);
    }

    public DOMException(String message, Exception e) {
        super(message, e);
    }

    public DOMException(Exception e) {
        super(e);
    }

    public DOMException() {
        super();
    }
}
