package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.PaymentDetailsDto;
import edu.icet.clothifybackend.entity.AddressEntity;
import edu.icet.clothifybackend.entity.PaymentDetailsEntity;
import edu.icet.clothifybackend.entity.User;
import edu.icet.clothifybackend.exception.UserNotFoundException;
import edu.icet.clothifybackend.repository.PaymentDetailsRepository;
import edu.icet.clothifybackend.repository.UserRepository;
import edu.icet.clothifybackend.service.PaymentDetailsService;
import edu.icet.clothifybackend.service.config.PaymentDetailsMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final PaymentDetailsRepository paymentRepository;
    private final UserRepository userRepository;
    private final PaymentDetailsMapper mapper;
    @Override
    public PaymentDetailsDto savePaymentDetails(PaymentDetailsDto dto) {
        //Throw exception when user not found
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //convert paymentDetailsDto to entity and save
        PaymentDetailsEntity savedPaymentEntity = paymentRepository.save(mapper.convertDtoToEntity(dto, user));

        //Convert saved entity into dto and return
        return mapper.convertEntityToDto(savedPaymentEntity);
    }

    @Override
    public List<PaymentDetailsDto> getPaymentDetailsListByUsername(String username) {
        return null;
    }

    @Override
    public Long deletePaymentDetails(Long id) {
        return null;
    }

    @Override
    public PaymentDetailsDto updatePaymentDetails(PaymentDetailsDto dto) {
        return null;
    }
}
