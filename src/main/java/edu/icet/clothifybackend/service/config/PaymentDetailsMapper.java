package edu.icet.clothifybackend.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.AddressDto;
import edu.icet.clothifybackend.dto.PaymentDetailsDto;
import edu.icet.clothifybackend.entity.AddressEntity;
import edu.icet.clothifybackend.entity.PaymentDetailsEntity;
import edu.icet.clothifybackend.entity.User;
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
