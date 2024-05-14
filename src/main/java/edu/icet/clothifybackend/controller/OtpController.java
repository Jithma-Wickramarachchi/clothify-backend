package edu.icet.clothifybackend.controller;

import edu.icet.clothifybackend.security.AuthenticationResponse;
import edu.icet.clothifybackend.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/otp")
public class OtpController {

    private final OtpService service;
    @PostMapping("/send-otp/{username}")
    public ResponseEntity<String> sendOtp(@PathVariable String username){
        service.sendOtp(username);
        return ResponseEntity.ok("OTP has been sent. Check your emails!");
    }
    @PostMapping("/verify-otp/{username}/{otp}")
    public AuthenticationResponse verifyOtp(@PathVariable String username, @PathVariable Integer otp){
        return service.verifyOtp(username, otp);
    }
}
