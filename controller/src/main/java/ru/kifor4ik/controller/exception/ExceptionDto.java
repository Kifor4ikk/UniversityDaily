package ru.kifor4ik.controller.exception;


public class ExceptionDto {

    private String cause;
    private String code;

    public ExceptionDto(String cause, String code) {
        this.cause = cause;
        this.code = code;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
