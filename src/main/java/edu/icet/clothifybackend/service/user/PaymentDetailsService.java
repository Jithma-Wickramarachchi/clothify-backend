package edu.icet.clothifybackend.service.user;

import edu.icet.clothifybackend.dto.user.PaymentDetailsDto;

import java.util.List;

public interface PaymentDetailsService {
    PaymentDetailsDto savePaymentDetails(PaymentDetailsDto dto);
    List<PaymentDetailsDto> getPaymentDetailsListByUsername(String username);
    Long deletePaymentDetails(Long id);
    PaymentDetailsDto updatePaymentDetails(PaymentDetailsDto dto);
}
