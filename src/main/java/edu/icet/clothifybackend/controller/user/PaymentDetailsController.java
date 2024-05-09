package edu.icet.clothifybackend.controller.user;

import edu.icet.clothifybackend.dto.user.PaymentDetailsDto;
import edu.icet.clothifybackend.service.user.PaymentDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/payment")
public class PaymentDetailsController {
    private final PaymentDetailsService service;
    @PostMapping
    public ResponseEntity<PaymentDetailsDto> savePaymentDetails(@Valid @RequestBody PaymentDetailsDto dto){
        return new ResponseEntity<>(service.savePaymentDetails(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<PaymentDetailsDto>> getPaymentDetailsListByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.getPaymentDetailsListByUsername(username), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentDetails(@PathVariable Long id){
        Long deletedPaymentId = service.deletePaymentDetails(id);
        return new ResponseEntity<>("Payment Details("+deletedPaymentId+") has been deleted successfully!", HttpStatus.OK);
    }
}
