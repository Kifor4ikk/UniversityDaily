package ru.kifor4ik.exception;

public class MainException extends RuntimeException {

    private String cause;
    private String exceptionId;

    public MainException(String message, String cause, String exceptionId) {
        super(message);
        this.cause = cause;
        this.exceptionId = exceptionId;
    }
}
