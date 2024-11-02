package com.maternease.maternease.exception;

import com.maternease.maternease.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MotherNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleMotherNotFound(MotherNotFoundException ex) {
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("404");
        response.setResponseMzg(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ResponseDTO> handleInvalidData(InvalidDataException ex) {
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("400");
        response.setResponseMzg(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGeneralException(Exception ex) {
        ResponseDTO response = new ResponseDTO();
        response.setResponseCode("500");
        response.setResponseMzg("An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

