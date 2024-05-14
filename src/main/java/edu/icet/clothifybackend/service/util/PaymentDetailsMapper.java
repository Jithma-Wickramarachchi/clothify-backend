package edu.icet.clothifybackend.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.user.PaymentDetailsDto;
import edu.icet.clothifybackend.entity.user.PaymentDetailsEntity;
import edu.icet.clothifybackend.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentDetailsMapper {

    private final ObjectMapper mapper;

    public PaymentDetailsDto convertEntityToDto(PaymentDetailsEntity paymentEntity){
        PaymentDetailsDto paymentDetailsDto = mapper.convertValue(paymentEntity, PaymentDetailsDto.class);
        paymentDetailsDto.setUsername(paymentEntity.getUser().getUsername());
        return paymentDetailsDto;
    }

    public PaymentDetailsEntity convertDtoToEntity(PaymentDetailsDto paymentDto, User user){
        PaymentDetailsEntity paymentEntity = mapper.convertValue(paymentDto, PaymentDetailsEntity.class);
        paymentEntity.setUser(user);
        return paymentEntity;
    }
}
