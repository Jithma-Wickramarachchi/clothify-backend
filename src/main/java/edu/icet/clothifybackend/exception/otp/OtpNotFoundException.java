package edu.icet.clothifybackend.exception.otp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "OTP not found")
public class OtpNotFoundException extends RuntimeException{
    public OtpNotFoundException(String username){
        super("OTP not found! username:"+username);
    }
}
