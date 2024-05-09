package edu.icet.clothifybackend.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "contact number not found")
public class ContactNumberNotFoundException extends RuntimeException{
    public ContactNumberNotFoundException(Long id){
        super("Contact number not found! id:"+id);
    }
}
