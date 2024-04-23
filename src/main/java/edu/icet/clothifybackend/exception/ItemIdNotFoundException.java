package edu.icet.clothifybackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item id not found")
public class ItemIdNotFoundException extends RuntimeException{
    public ItemIdNotFoundException(Long id){
        super("Item Id not found! id:"+id);
    }
}
