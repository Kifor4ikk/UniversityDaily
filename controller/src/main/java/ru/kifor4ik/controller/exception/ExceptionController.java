package ru.kifor4ik.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.kifor4ik.Main;
import ru.kifor4ik.exception.MainException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({MainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto badRequest(MainException exception, WebRequest request) {
        return new ExceptionDto(exception.getLocalizedMessage(), exception.getCode());
    }
}
