package edu.icet.clothifybackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Address not found")
public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(Long id){
        super("Address not found! Id:"+id);
    }
}
