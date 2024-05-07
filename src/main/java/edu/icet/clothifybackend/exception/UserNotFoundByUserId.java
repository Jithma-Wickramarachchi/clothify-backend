package edu.icet.clothifybackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Item image not found")
public class UserNotFoundByUserId extends RuntimeException{
    public UserNotFoundByUserId(Long id){
        super("User not found! Id:"+id);
    }
}
