package com.epam.easyjet.step.exception;

/**
 * Created by Yauheni_Borbut on 3/13/2017.
 */
public class StepException extends RuntimeException {

    public StepException() {
    }

    public StepException(String message) {
        super(message);
    }

    public StepException(String message, Exception e) {
        super(message, e);
    }

    public StepException(Exception e) {
        super(e);
    }
}
