package edu.icet.clothifybackend.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ordered Item not found")
public class OrderedItemNotFoundException extends RuntimeException{
    public OrderedItemNotFoundException(Long id){
        super("Ordered Item not found! Id:"+id);
    }

}
