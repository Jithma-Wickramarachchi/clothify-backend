package edu.icet.clothifybackend.controller.user;

import edu.icet.clothifybackend.dto.user.AddressDto;
import edu.icet.clothifybackend.dto.user.PaymentDetailsDto;
import edu.icet.clothifybackend.service.user.PaymentDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/payment")
public class PaymentDetailsController {
    private final PaymentDetailsService service;
    @PostMapping
    public ResponseEntity<PaymentDetailsDto> savePaymentDetails(@Valid @RequestBody PaymentDetailsDto dto){
        return new ResponseEntity<>(service.savePaymentDetails(dto), HttpStatus.CREATED);
    }
}
