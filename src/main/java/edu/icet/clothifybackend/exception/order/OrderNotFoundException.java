package edu.icet.clothifybackend.exception.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super("Order not found! Id:"+id);
    }
}
