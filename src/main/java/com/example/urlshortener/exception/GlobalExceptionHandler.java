package com.example.urlshortener.exception;

import com.example.urlshortener.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(
            UrlNotFoundException.class
    )
    public ResponseEntity<ErrorResponse>
    handleUrlNotFound(

            UrlNotFoundException ex

    ){

        ErrorResponse response =
                ErrorResponse.builder()

                        .timestamp(
                                LocalDateTime.now()
                        )

                        .status(
                                HttpStatus.NOT_FOUND.value()
                        )

                        .message(
                                ex.getMessage()
                        )

                        .build();


        return ResponseEntity
                .status(
                        HttpStatus.NOT_FOUND
                )
                .body(
                        response
                );

    }

    @ExceptionHandler(
            org.springframework.web.bind.MethodArgumentNotValidException.class
    )
    public ResponseEntity<ErrorResponse>
    handleValidationException(

            org.springframework.web.bind.MethodArgumentNotValidException ex

    ){

        String message =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();


        ErrorResponse response =
                ErrorResponse.builder()

                        .timestamp(
                                java.time.LocalDateTime.now()
                        )

                        .status(
                                HttpStatus.BAD_REQUEST.value()
                        )

                        .message(
                                message
                        )

                        .build();


        return ResponseEntity
                .badRequest()
                .body(
                        response
                );

    }

}