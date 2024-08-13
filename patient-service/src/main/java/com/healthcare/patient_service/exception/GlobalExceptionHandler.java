package com.healthcare.patient_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({PatientNotFoundException.class})
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex){
            log.error("Patient not Found : {}",ex.getMessage());
            Map<String,String> response = new HashMap<>();
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);

        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }



}
