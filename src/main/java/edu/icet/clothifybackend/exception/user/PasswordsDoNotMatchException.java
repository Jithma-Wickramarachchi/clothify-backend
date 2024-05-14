package edu.icet.clothifybackend.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "passwords do not match")
public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(String username){
        super("Passwords do not match! username:"+username);
    }
}
