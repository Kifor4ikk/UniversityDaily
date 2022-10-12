package ru.kifor4ik.exception;

public class SoftDeleteException extends MainException {
    public SoftDeleteException(String message, String cause, String exceptionId) {
        super(message, cause, exceptionId);
    }
}
