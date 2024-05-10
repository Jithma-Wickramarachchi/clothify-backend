package edu.icet.clothifybackend.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User image not found")
public class UserImageNotFoundException extends RuntimeException{
    public UserImageNotFoundException(String username){
        super("User image not found! username:"+username);
    }
}
