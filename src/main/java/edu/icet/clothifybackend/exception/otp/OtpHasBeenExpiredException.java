package edu.icet.clothifybackend.exception.otp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "OTP has been expired")
public class OtpHasBeenExpiredException extends RuntimeException {
    public OtpHasBeenExpiredException(String username) {
        super("OTP has been expired! username:" + username);
    }
}