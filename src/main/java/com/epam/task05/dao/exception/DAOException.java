package com.epam.task05.dao.exception;

/**
 * Created by Yauheni_Borbut on 2/16/2017.
 */
public class DAOException extends Exception{

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException() {
        super();
    }
}
