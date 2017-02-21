package com.epam.task05.dao.parser.exception;

/**
 * Created by Yauheni_Borbut on 2/21/2017.
 */
public class ParserException extends Exception{

    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Exception e) {
        super(message, e);
    }

    public ParserException(Exception e) {
        super(e);
    }

    public ParserException() {
        super();
    }
}
