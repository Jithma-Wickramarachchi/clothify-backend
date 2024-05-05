package edu.icet.clothifybackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item image not found")
public class ItemImageNotFoundException extends RuntimeException {
    public ItemImageNotFoundException(Long id){
        super("Item image not found! Id:"+id);
    }
}
