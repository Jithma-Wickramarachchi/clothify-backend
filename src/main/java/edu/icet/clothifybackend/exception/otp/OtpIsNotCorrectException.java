package edu.icet.clothifybackend.exception.otp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "OTP is not correct")
public class OtpIsNotCorrectException extends RuntimeException{
    public OtpIsNotCorrectException(String username){
        super("OTP is not correct! username:"+username);
    }
}
