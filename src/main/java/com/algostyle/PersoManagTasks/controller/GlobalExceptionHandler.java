package com.algostyle.PersoManagTasks.controller;

import com.algostyle.PersoManagTasks.exception.AlreadyExistsCategoryException;
import com.algostyle.PersoManagTasks.exception.CategoryNotFoundException;
import com.algostyle.PersoManagTasks.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsCategoryException.class)
    public ResponseEntity<String> handleAlreadyExistsCategoryException(AlreadyExistsCategoryException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public  ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

}
