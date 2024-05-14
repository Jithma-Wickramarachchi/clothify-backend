package edu.icet.clothifybackend.controller.user;

import edu.icet.clothifybackend.service.user.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/change-password")
public class ChangePasswordController {
    private final ChangePasswordService service;
    @PostMapping
    public ResponseEntity<String> changePassword(@RequestBody PasswordDto passwordDto){
        service.changePassword(passwordDto);
        return ResponseEntity.ok("Password has been changed successfully!");
    }

}
