package edu.icet.clothifybackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username){
        super("User not found! username:"+username);
    }
}
