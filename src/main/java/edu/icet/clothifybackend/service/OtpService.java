package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.security.AuthenticationResponse;

public interface OtpService {
    void sendOtp(String username);
    AuthenticationResponse verifyOtp(String username, Integer otp);
}
