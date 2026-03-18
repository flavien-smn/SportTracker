package com.flavien.sporttracker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flavien.sporttracker.exception.customException.ConflictException;
import com.flavien.sporttracker.exception.customException.NotFoundException;
import com.flavien.sporttracker.exception.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException e) {
        return ResponseEntity.status(409)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(409)
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(404)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(404)
                        .build());
    }

}
