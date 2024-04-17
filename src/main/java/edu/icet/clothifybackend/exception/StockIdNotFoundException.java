package edu.icet.clothifybackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Stock id not found")
public class StockIdNotFoundException extends RuntimeException{
    public StockIdNotFoundException(String error){
        super(error);
    }
}
