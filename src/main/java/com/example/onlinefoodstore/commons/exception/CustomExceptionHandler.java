package com.example.onlinefoodstore.commons.exception;

import com.example.onlinefoodstore.model.dto.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {
    @ExceptionHandler(value = {GeneralApiException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Header<?> handleGeneralApiException(GeneralApiException exception) {
        log.error("Handled Exception {0}", exception);
        return Header.error(exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Header<?> handleValidationErrors(MethodArgumentNotValidException exception) {
        log.error("Handled Exception {0}", exception);
        return Header.error(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage());
    }
}