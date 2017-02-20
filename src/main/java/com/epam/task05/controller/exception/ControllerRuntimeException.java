package com.epam.task05.controller.exception;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public class ControllerRuntimeException extends RuntimeException {

    public ControllerRuntimeException() {
        super();
    }

    public ControllerRuntimeException(String message) {
        super(message);
    }

    public ControllerRuntimeException(String message, Exception e) {
        super(message, e);
    }

    public ControllerRuntimeException(Exception e) {
        super(e);
    }
}
