package com.ecom.Ecom.configs;

import com.ecom.Ecom.exception.FakeStoreProductNotFoundException;
import com.ecom.Ecom.exceptionDtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends Exception{

    //Handling exception globally.
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(FakeStoreProductNotFoundException exception){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
