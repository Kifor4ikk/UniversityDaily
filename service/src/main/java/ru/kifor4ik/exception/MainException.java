package ru.kifor4ik.exception;

public class MainException extends RuntimeException {

    public MainException(String message, String cause, String exceptionId) {
        super("Exception: " + message + "\n Cause: " + cause + "\n ExceptionId: " + exceptionId);
    }
}
