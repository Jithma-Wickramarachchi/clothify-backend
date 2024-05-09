package edu.icet.clothifybackend.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "contact number not found")
public class ContactNumberNotFoundException extends RuntimeException{
    ContactNumberNotFoundException(String contact){
        super("Contact number not found! number:"+contact);
    }
}
