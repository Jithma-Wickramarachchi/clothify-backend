package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.AddressDto;
import edu.icet.clothifybackend.dto.PaymentDetailsDto;

import java.util.List;

public interface PaymentDetailsService {
    PaymentDetailsDto savePaymentDetails(PaymentDetailsDto dto);
    List<PaymentDetailsDto> getPaymentDetailsListByUsername(String username);
    Long deletePaymentDetails(Long id);
    PaymentDetailsDto updatePaymentDetails(PaymentDetailsDto dto);
}
