package edu.icet.clothifybackend.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "payment details not found")
public class PaymentDetailsNotFoundException extends RuntimeException{
    public PaymentDetailsNotFoundException(Long id){
        super("Payment Details not found! Id:"+id);
    }
}
